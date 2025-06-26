package deepleaper.video;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频拼接
 *         <dependency>
 *             <groupId>org.bytedeco</groupId>
 *             <artifactId>javacv</artifactId>
 *             <version>1.5.8</version>
 *         </dependency>
 */

public class VideoConcatenation {


    /**
     * 视频拼接
     * A文件夹的视频挨个与B文件夹的视频拼接，输出到C文件夹。
     * mp4文件名称前缀相同 prefix 位数的则可匹配，如果prefix = 0则全匹配。
     */
    static int prefix = 2;
    static String sourceDirPath1 = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/A片段";
    static String sourceDirPath2 = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/B片段";
    static String targetDirPath  = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/target";
    static String commandFormat = "ffmpeg -i %s -i %s -filter_complex \"[0:v][0:a][1:v][1:a]concat=n=2:v=1:a=1[outv][outa]\" -map \"[outv]\" -map \"[outa]\" %s";

    public static void main(String[] args) throws Exception {

        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        List<String> videoNames1 = getValidVideos(sourceDirPath1);
        List<String> videoNames2 = getValidVideos(sourceDirPath2);
        for (String videoName1 : videoNames1) {
            for (String videoName2 : videoNames2) {
                if (!videoName1.substring(0, prefix).equals(videoName2.substring(0, prefix))) {
                    continue;
                }
                String targetVideoName = videoName1.substring(0, videoName1.lastIndexOf(".mp4")) + "__" + videoName2.substring(0, videoName2.lastIndexOf(".mp4")) + ".mp4";
                String command = String.format(commandFormat, sourceDirPath1 + "/" + videoName1, sourceDirPath2 + "/" + videoName2, targetDirPath + "/" + targetVideoName);
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
