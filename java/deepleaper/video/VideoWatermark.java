package deepleaper.video;



import java.io.BufferedReader;
import java.io.File;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: liyangjin
 * @create: 2023-12-13 14:13
 * @Description:
 */
public class VideoWatermark {


    /**
     * 为视频添加水印
     * 视频文件夹里的所有视频 * 图片文件夹里的所有图片
     * 输出到 目标视频文件夹，不考虑尺寸
     */
    static String videoDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/A片段";
    static String watermarkDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/水印";
    static String targetDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/target";
    static String commandFormat = "ffmpeg -i %s -vf \"movie=%s [watermark]; [in][watermark] overlay=W-w-10:H-h-10 [out]\" %s";

    public static void main(String[] args) throws Exception {
        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        List<String> videoNames = getValidVideos(videoDirPath);
        List<String> imageNames = getValidImage(watermarkDirPath);
        for (String videoName : videoNames) {
            for (String imageName : imageNames) {
                String targetVideoName = videoName.substring(0, videoName.lastIndexOf(".mp4")) + "__" + imageName.substring(0, imageName.lastIndexOf(".")) + ".mp4";
                String command = String.format(commandFormat, videoDirPath + "/" + videoName, watermarkDirPath + "/" + imageName, targetDirPath + "/" + targetVideoName);
                System.out.println(command);
                try {
                    executeCommand(command);
                } catch (Exception e) {
                    System.err.println("执行命令失败：" + command);
                }
            }
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


    private static List<String> getValidImage(String dirPath) throws Exception {
        File dirFile = new File(dirPath);
        if (!dirFile.exists() || !dirFile.isDirectory() ) {
            throw new Exception("文件夹路径不存在 或者 文件夹为空 dirPath=" + dirPath);
        }
        List<String> videoNames = new ArrayList<>();
        File[] files = dirFile.listFiles();
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                videoNames.add(file.getName());
            }
        }
        return videoNames;
    }

    private static List<String> getValidVideos(String dirPath) throws Exception {
        File dirFile = new File(dirPath);
        if (!dirFile.exists() || !dirFile.isDirectory() ) {
            throw new Exception("文件夹路径不存在 或者 文件夹为空 dirPath=" + dirPath);
        }
        List<String> videoNames = new ArrayList<>();
        File[] files = dirFile.listFiles();
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith(".mp4")) {
                videoNames.add(file.getName());
            }
        }
        return videoNames;
    }

}
