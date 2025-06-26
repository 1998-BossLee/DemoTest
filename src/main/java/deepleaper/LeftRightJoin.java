package deepleaper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2023-06-08 11:20
 * @Description:
 */
public class LeftRightJoin {

    static String leftDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/双拼图-左图";
    static String rightDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/双拼图-右图";
    static String targetDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/双拼图-target";
    static int width = 1280, height = 720, num = 5;
    public static void main(String[] args) throws Exception{
        checkDirPath(leftDirPath);
        checkDirPath(rightDirPath);
        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        //1、获取图片
        List<String> leftPicNames = getValidImageFiles(leftDirPath);
        List<String> rightPicNames = getValidImageFiles(rightDirPath);
        //2、拼接
        for (int i = 0; i < num && i < leftPicNames.size() && i < rightPicNames.size(); i++) {
            try {
                File leftFile = new File(leftDirPath + File.separator + leftPicNames.get(i));
                BufferedImage leftImage = ImageIO.read(leftFile);
                File rightFile = new File(rightDirPath + File.separator + rightPicNames.get(i));
                BufferedImage rightImage = ImageIO.read(rightFile);
                BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                targetImage.getGraphics().drawImage(leftImage, 0, 0, null);
                targetImage.getGraphics().drawImage(rightImage, leftImage.getWidth(), 0, null);
                String targetFileName = leftPicNames.get(i).substring(0, leftPicNames.get(i).length() - 4) + "_"
                        + rightPicNames.get(i).substring(0, rightPicNames.get(i).length() - 4) + ".png";
                String targetFilePath = targetDirPath + File.separator + targetFileName;
                ImageIO.write(targetImage, "png", new File(targetFilePath));
                System.out.println("拼接成功 " + targetFilePath);
            } catch (Exception e) {
                System.err.println(String.format("拼图失败 leftImageName=%s rightImageName=%s", leftPicNames.get(i), rightPicNames.get(i)));
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
                if (file.isFile() && file.getName().toLowerCase().endsWith(".png")) {
                    imageFiles.add(file.getName());
                }
            }
        }
        return imageFiles;
    }
}
