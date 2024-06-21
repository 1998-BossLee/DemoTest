package deepleaper.video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteLinuxCommand {

    public static void main(String[] args) {
        String ffmpegCommand = "ffmpeg -i /Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/A片段/01AI.mp4 -vf \"movie=/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/水印/1.png [watermark]; [in][watermark] overlay=W-w-10:H-h-10 [out]\" /Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/target/water-mark3.mp4";

        try {
            executeCommand(ffmpegCommand);
        } catch (Exception e) {
            e.printStackTrace();
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
}
