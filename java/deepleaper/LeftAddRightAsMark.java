package deepleaper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2023-06-13 10:56
 * @Description: 左图和右图重叠，达到加水印效果
 * 1、无脑一对多
 * 2、前缀相同一对多
 */
public class LeftAddRightAsMark {

    static String leftDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/水印图-左图";
    static String rightDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/水印图-右图";
    static String targetDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/水印图-target";

    //这里是类型，需要手动填写
    static int type = 2;
    //前缀位数
    static int prefixLen = 7;

    public static void main(String[] args) throws Exception {
        checkDirPath(leftDirPath);
        checkDirPath(rightDirPath);
        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        List<String> leftPicNames = getValidImageFiles(leftDirPath);
        List<String> rightPicNames = getValidImageFiles(rightDirPath);
        for (String leftPicName : leftPicNames) {
            for (String rightPicName : rightPicNames) {
                try {
                    switch (type) {
                        //无脑一对多
                        case 1:
                            break;
                        //前缀相同一对多
                        case 2:
                            if (!leftPicName.substring(0, prefixLen).equals(rightPicName.substring(0, prefixLen))) {
                                continue;
                            }
                            break;
                    }
                    File leftFile = new File(leftDirPath + File.separator + leftPicName);
                    BufferedImage leftImage = ImageIO.read(leftFile);
                    File rightFile = new File(rightDirPath + File.separator + rightPicName);
                    BufferedImage rightImage = ImageIO.read(rightFile);
                    if (leftImage.getWidth() != rightImage.getWidth() || leftImage.getHeight() != rightImage.getHeight()) {
                        System.err.println(leftPicName + " 图片尺寸不一致");
                        continue;
                    }
                    Graphics2D g2d = leftImage.createGraphics();
                    g2d.drawImage(leftImage, 0, 0, null);
                    AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9F);
                    g2d.setComposite(alpha);
                    g2d.drawImage(rightImage, 0, 0, null);
                    String leftNameWithoutSuf = leftPicName.substring(0, leftPicName.length() - 4);
                    String rightNameWithoutSuf = rightPicName.substring(0, rightPicName.length() - 4);
                    ImageIO.write(leftImage, "png", new File(targetDirPath + File.separator + (leftNameWithoutSuf + "_" + rightNameWithoutSuf + ".png")));
                    System.err.println(leftPicName + " 图片添加水印成功 successful");
                } catch (Exception e) {
                    System.err.println(leftPicName + " 图片添加水印失败");
                    e.printStackTrace();
                }
            }
        }

    }

    private static void checkDirPath(String dirPath) throws Exception {
        File dirFile = new File(dirPath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            throw new Exception("文件夹路径不存在 dirPath=" + dirPath);
        }
    }

    private static List<String> getValidImageFiles(String dirPath) {
        List<String> imageFiles = new ArrayList<>();
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isFile()) {
                    continue;
                }
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
                    imageFiles.add(file.getName());
                }
            }
        }
        return imageFiles;
    }

}
