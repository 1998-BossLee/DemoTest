package deepleaper.video;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2024-09-13 18:10
 * @Description:
 * 1、读取水印文件夹里的水印，mov格式。
 * 2、给视频添加水印。
 */
public class BatchAddVideoWatermark {


    public static void main(String[] args) throws Exception {

        String sourceVideoDirPath = "C:\\Users\\Administrator\\Desktop\\姣捷的图片\\剪辑脚本需要\\2-需要加水印片段";
        String watermarkDirPath = "C:\\Users\\Administrator\\Desktop\\姣捷的图片\\剪辑脚本需要\\2-视频水印";
        String targetDirPath = "C:\\Users\\Administrator\\Desktop\\姣捷的图片\\剪辑脚本需要\\2-需要加水印片段target";

        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        String videoWatermarkName = getVideoWatermarkPath(watermarkDirPath);
        if (videoWatermarkName == null) {
            System.err.println("找不到视频水印文件 watermarkDirPath=" + watermarkDirPath);
            return;
        }
        List<String> videoNames = getValidVideos(sourceVideoDirPath);
        String videoWatermarkPath = watermarkDirPath + File.separator + videoWatermarkName;
        for (String videoName : videoNames) {
            String targetVideoPath = targetDirPath + "/" + videoName;
            String sourceVideoPath = sourceVideoDirPath + "/" + videoName;
            try {
                long t1 = System.currentTimeMillis();
                executeCommandWindow(sourceVideoPath, videoWatermarkPath, targetVideoPath);
                System.out.println(targetVideoPath + "完成 耗时" + (System.currentTimeMillis() - t1)/1000 + "秒");
            } catch (Exception e) {
                System.err.println(targetVideoPath + "异常");
            }
        }
    }

    private static String getVideoWatermarkPath(String videoWatermarkDirPath) throws Exception {
        File videoWatermarkDir = new File(videoWatermarkDirPath);
        if (!videoWatermarkDir.exists()) {
            throw new Exception("视频水印文件夹路径不存在 videoWatermarkDirPath=" + videoWatermarkDirPath);
        }
        for (File file : videoWatermarkDir.listFiles()) {
            if (!file.isFile()) {
                continue;
            }
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith(".mov")) {
                return fileName;
            }
        }
        return null;
    }

    private static void executeCommandMac(String command) throws Exception {
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

    private static void executeCommandWindow(String videoPath, String watermarkPath, String outputPath) throws Exception {

        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffmpeg",
                "-i", videoPath,
                "-i", watermarkPath,
                "-filter_complex", "overlay=(main_w-overlay_w):(main_h-overlay_h):shortest=1",
                "-codec:a", "copy",
                outputPath
        );

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
