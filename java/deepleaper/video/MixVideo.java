package deepleaper.video;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2024-12-02 16:30
 * @Description:
 */
public class MixVideo {

    public static void main(String[] args) {
        // 设置文件夹路径和参数
        String sourceDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/4-测试分片段"; // 输入文件夹路径
        String targetDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/4-target"; // 输出文件夹路径
        int a = 5; // 要生成的总视频数量
        int b = 10; // 每个视频由 b 个视频片段组成

        // 获取文件夹中的视频片段
        List<String> videoFiles = getVideoFiles(sourceDirPath);

        for (int i = 0; i < a; i++) {
            // 随机选取 b 个视频片段
            Collections.shuffle(videoFiles);
            List<String> selectedVideos = videoFiles.subList(0, b);

            // 拼接视频
            String outputVideoPath = targetDirPath + "mixed_video_" + (i + 1) + ".mp4";
            try {
                long ts = System.currentTimeMillis();
                File outputVideo = new File(outputVideoPath);
                if (outputVideo.exists()) {
                    outputVideo.delete();
                }
                createMixedVideo(selectedVideos, outputVideoPath);
                System.out.println("视频 " + (i + 1) + " 已生成：" + outputVideoPath + "，耗时："+ (System.currentTimeMillis()-ts)/1000 + "秒");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取指定文件夹中的所有视频文件
    public static List<String> getVideoFiles(String directoryPath) {
        List<String> videoFiles = new ArrayList<>();
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".mp4"));
            if (files != null) {
                for (File file : files) {
                    videoFiles.add(file.getAbsolutePath());
                }
            }
        }
        return videoFiles;
    }

    // 使用 FFmpeg 拼接多个视频片段
    public static void createMixedVideo(List<String> videoFiles, String outputFilePath) throws IOException {
        // 生成 FFmpeg 的输入文件列表
        String inputFileListPath = "input_file_list.txt";
        Files.write(Paths.get(inputFileListPath), generateInputList(videoFiles));

        String osName = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder;
        String command = String.format("ffmpeg -f concat -safe 0 -i %s -c copy %s", inputFileListPath, outputFilePath);
        if (osName.contains("win")) {
            // Windows
            processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
        } else {
            // macOS/Linux
            processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
        }
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try {
            process.waitFor(); // 等待 FFmpeg 执行完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            new File(inputFileListPath).delete(); // 删除临时文件
        }
    }

    // 生成 FFmpeg 输入文件列表的内容
    public static List<String> generateInputList(List<String> videoFiles) {
        List<String> inputList = new ArrayList<>();
        for (String videoFile : videoFiles) {
            inputList.add("file '" + videoFile + "'");
        }
        return inputList;
    }
}
