package deepleaper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author: liyangjin
 * @create: 2023-04-28 17:24
 * @Description: 递归三拼图
 */
public class Main {

    /**
     * 1、输入源文件夹，将3张图片按左右顺序拼后输出到目标文件夹
     * 2、三拼组合，同样3张图只能合成1张图。1-2-3存在，则不要2-3-1，但是1-2-4可以保留。C(3, n)，假设5张图分别是1、2、3、4、5，成品图有10张。
     * 1-2-3、1-2-4、1-2-5、1-3-4、1-3-5、1-4-5、2-3-4、2-3-5、2-4-5、3-4-5
     * 3、拼接成的尺寸自定义 with * height，输入的图片会保证height相同。
     * 4、拼接完之后在原图片上再盖一层蒙版。如果蒙版图片不存在则不覆盖。
     */

    static String sourceDirPath = "/Users/liyangjin/Desktop/姣捷的图片/三拼图测试";
    static String markPicPath = "/Users/liyangjin/Desktop/姣捷的图片/mark.png";
    static int width = 1280, height = 720;
    public static void main(String[] args) {
        dfs(sourceDirPath);
    }

    //递归遍历文件夹
    private static void dfs(String curDirPath) {
        //1、验证当前文件夹路径
        File sourceDir = new File(curDirPath);
        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            System.err.println("源文件夹路径错误 sourceDirPath=" + curDirPath);
            return;
        }
        File[] subdirectories = sourceDir.listFiles();
        if (subdirectories == null) {
            System.err.println("源文件夹下没有文件 sourceDirPath=" + curDirPath);
            return;
        }
        //2、遍历
        for (File subFile : subdirectories) {
            if (subFile.getName().endsWith(".png")) {
                //2.1 叶子文件夹，处理图片
                process(curDirPath);
                return;
            }
            //2.2 继续遍历子文件夹
            if (!subFile.isHidden()) {
                dfs(subFile.getAbsolutePath());
            }
        }
    }

    private static void process(String curDirPath) {
        if (curDirPath.endsWith("-target")) {
            return;
        }
        String targetDirPath = curDirPath + "-target";
        File subTargetDir = new File(targetDirPath);
        if (!subTargetDir.exists()) {
            subTargetDir.mkdirs();
        } else {
            //不小心运行多次，不再重复处理
            return;
        }
        List<String> imageFiles = getValidImageFiles(curDirPath);
        List<List<String>> combinations = generateCombinations(imageFiles);

        for (int i = 0; i < combinations.size(); i++) {
            List<String> combination = combinations.get(i);
            BufferedImage combinedImage = combineImages(curDirPath, combination);
            //失败，跳过
            if (combinedImage == null) {
                continue;
            }
            //移除【.png】后缀
            List<String> removeSufList = combination.stream().map(it -> it.substring(0, it.length() - 4)).sorted().collect(Collectors.toList());
            String combinedFilename = String.join("-", removeSufList) + ".png";
            String combinedFilePath = targetDirPath + File.separator + combinedFilename;
            try {
                ImageIO.write(combinedImage, "png", new File(combinedFilePath));
                System.out.println("Saved combined image " + (i + 1) + "/" + combinations.size() + ": " + combinedFilePath);
            } catch (IOException e) {
                System.out.println("Failed to save combined image: " + combinedFilePath);
                e.printStackTrace();
            }
        }
    }


    private static List<String> getValidImageFiles(String dirPath) {
        List<String> imageFiles = new ArrayList<>();
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".png")) {
                    imageFiles.add(file.getName());
                }
            }
        }
        return imageFiles;
    }

    private static List<List<String>> generateCombinations(List<String> imageFiles) {
        List<List<String>> combinations = new ArrayList<>();
        for (int i = 0; i < imageFiles.size(); i++) {
            for (int j = i + 1; j < imageFiles.size(); j++) {
                for (int k = j + 1; k < imageFiles.size(); k++) {
                    List<String> combination = new ArrayList<>();
                    combination.add(imageFiles.get(i));
                    combination.add(imageFiles.get(j));
                    combination.add(imageFiles.get(k));
                    combinations.add(combination);
                }
            }
        }
        return combinations;
    }

    private static BufferedImage combineImages(String imagesDir, List<String> combination) {
        int numImages = combination.size();
        BufferedImage[] images = new BufferedImage[numImages];

        try {
            for (int i = 0; i < numImages; i++) {
                File imageFile = new File(imagesDir + File.separator + combination.get(i));
                BufferedImage image = ImageIO.read(imageFile);
                images[i] = image;
            }
            BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            int x = 0, y = 0;
            for (BufferedImage image : images) {
                combinedImage.getGraphics().drawImage(image, x, y, null);
                x += image.getWidth();
            }
            //添加蒙版
            if (markPicPath != null) {
                File markPic = new File(markPicPath);
                BufferedImage markPicBuffer = ImageIO.read(markPic);
                Graphics2D g2d = combinedImage.createGraphics();
                g2d.drawImage(combinedImage, 0, 0, null);
                AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
                g2d.setComposite(alpha);
                g2d.drawImage(markPicBuffer, 0, 0, null);
            }
            return combinedImage;
        } catch (IOException e) {
            System.out.println("拼接图片失败，combination=" + combination);
            e.printStackTrace();
            return null;
        }
    }


}
