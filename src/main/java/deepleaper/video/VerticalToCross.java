package deepleaper.video;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: liyangjin
 * @create: 2025-01-03 17:56
 * @Description:
 *
 * 默认原视频是720*1280
 * 先处理成640*1080，再将640*1080的视频并列成3列，
 */
public class VerticalToCross {

    private static String os = System.getProperty("os.name");

    public static void main(String[] args) throws Exception {
        String sourceDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/7-竖屏转3横"; // 替换为源文件夹路径
        String targetDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/7-竖屏转3横target"; // 替换为目标文件夹路径

        File sourceDir = new File(sourceDirPath);
        File targetDir = new File(targetDirPath);

        if (!targetDir.exists()) {
            targetDir.mkdirs(); // 创建目标文件夹
        }
        System.out.println(os);
        if (sourceDir.isDirectory()) {
            File[] files = sourceDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp4")); // 只处理mp4文件
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    String outputFilePath = targetDirPath + File.separator + fileName;
                    File outputFile = new File(outputFilePath);
                    if (outputFile.exists()) {
                        System.out.println(outputFilePath + "已存在，不进行二次处理");
                        continue;
                    }
                    String command = String.format("ffmpeg -i \"%s\" -i \"%s\" -i \"%s\" -filter_complex " +
                                    "\"[0:v]scale=640:1080[0v];[1:v]scale=640:1080[1v];[2:v]scale=640:1080[2v];" +
                                    "[0v][1v][2v]hstack=3,scale=1920:1080,setsar=1\" \"%s\"",
                            file.getAbsolutePath(), file.getAbsolutePath(), file.getAbsolutePath(), outputFilePath);
                    System.out.println(command);
                    try {
                        long ts = System.currentTimeMillis();
                        executeCommand(command);
                        System.out.println("处理完成 耗时:" + ((System.currentTimeMillis() - ts) / 1000) + "秒");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("源路径不是一个文件夹");
        }
    }

    private static void executeCommand(String command) throws Exception {
        if (os.equals("Mac OS X")) {
            executeCommandMac(command);
        } else {
            executeCommandWindow(command);
        }
    }

    private static void executeCommandMac(String command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
        Process process = processBuilder.start();

        // 读取命令执行的输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
        }

        int exitCode = process.waitFor();
        //System.out.println("Command exited with code: " + exitCode);

        // 处理错误输出
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
            //System.err.println(errorLine);
        }

        if (exitCode != 0) {
            throw new RuntimeException("Command execution failed with exit code: " + exitCode);
        }
    }

    private static void executeCommandWindow(String command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); // 设置编码为GBK
        String line;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("Command exited with code: " + exitCode);

        process.waitFor();
    }

}
