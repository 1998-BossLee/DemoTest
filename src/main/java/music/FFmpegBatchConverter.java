package music;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2024-06-22 00:03
 * @Description: 将一个文件夹里的mp3文件，把比特率调到320 kbps，音频采样率提高到48kHz，并在新的mp3文件后缀加上-320-48
 */
public class FFmpegBatchConverter {

    private static String dirPath = "/Users/liyangjin/Desktop/0AI音乐/我的声音";
    static String commandFormat = "ffmpeg -i \"%s\" -b:a 320k -ar 48000 \"%s\"";

    public static void main(String[] args) throws Exception {
        File dirFile = new File(dirPath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            throw new Exception("文件夹路径不存在 或者 文件夹为空 dirPath=" + dirPath);
        }

        //获取需要转化的mp3文件名
        File[] mp3Files = dirFile.listFiles((dir, name) -> !name.toLowerCase().endsWith("-320-48.mp3") && name.toLowerCase().endsWith(".mp3"));

        if (mp3Files == null || mp3Files.length == 0) {
            System.out.println("No MP3 files found in the specified folder.");
            return;
        }

        for (File file : mp3Files) {
            processFile(file);
        }
    }

    private static void processFile(File mp3File) throws Exception{
        String inputFilePath = mp3File.getAbsolutePath();
        String outputFilePath = generateOutputFilePath(inputFilePath);

        String command = String.format(commandFormat, inputFilePath, outputFilePath);

        try {
            executeCommand(command);
            System.out.println(command + " success");
        } catch (Exception e) {
            //System.err.println("FFmpegBatchConverter.processFile error " + e);
        }
    }

    private static void executeCommand(String command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
        Process process = processBuilder.start();

        // 读取命令执行的输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("Command exited with code: " + exitCode);

        // 处理错误输出
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
            System.err.println(errorLine);
        }
        if (exitCode != 0) {
            throw new RuntimeException("Command execution failed with exit code: " + exitCode);
        }
    }

    private static String generateOutputFilePath(String inputFilePath) {
        int dotIndex = inputFilePath.lastIndexOf('.');
        String baseName = inputFilePath.substring(0, dotIndex);
        String extension = inputFilePath.substring(dotIndex);
        return baseName + "-320-48" + extension;
    }

}
