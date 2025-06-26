package deepleaper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: liyangjin
 * @create: 2023-06-09 14:04
 * @Description:
 */
public class PicAddMark {

    static String picDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/0待添加蒙版的图";
    static String targetDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/0已添加蒙版的图";
    static String mark_1280_PicPath = "/Users/liyangjin/Desktop/0姣捷的图片/mark_1280.png";
    static BufferedImage mark_1280_image = null;
    static String mark_456_PicPath = "/Users/liyangjin/Desktop/0姣捷的图片/mark_456.png";
    static BufferedImage mark_456_image = null;
    static String mark_lanmei_PicPath = "/Users/liyangjin/Desktop/0姣捷的图片/mark_lanmei.png";
    static BufferedImage mark_lanmei_image = null;
    static String mark_xigua_PicPath = "/Users/liyangjin/Desktop/0姣捷的图片/mark_xigua.png";
    static BufferedImage mark_xigua_image = null;
    public static void main(String[] args) throws Exception{
        initMarkImage();
        checkDirPath(picDirPath);
        List<String> picNames = getValidImageFiles(picDirPath);

        for (String picName : picNames) {
            try {
                File file = new File(picDirPath  + File.separator + picName);
                BufferedImage targetImage = ImageIO.read(file);
                Graphics2D g2d = targetImage.createGraphics();
                g2d.drawImage(targetImage, 0, 0, null);
                AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
                g2d.setComposite(alpha);
                if (picName.contains("1280")) {
                    g2d.drawImage(mark_1280_image, 0, 0, null);
                }
                if (picName.contains("456")){
                    g2d.drawImage(mark_456_image, 0, 0, null);
                }
                if (picName.contains("蓝莓")) {
                    g2d.drawImage(mark_lanmei_image, 0, 0, null);
                }
                if (picName.contains("西瓜")) {
                    g2d.drawImage(mark_xigua_image, 0, 0, null);
                }
                ImageIO.write(targetImage, "png", new File(targetDirPath + File.separator + picName));
                System.out.println("添加蒙版成功 targetImageName=" + new File(targetDirPath + File.separator + picName));
            } catch (Exception e) {
                System.err.println("为图片添加蒙版异常 picName="+picName);

            }
        }
    }

    private static void initMarkImage() throws Exception{
        try {
            File file = new File(mark_1280_PicPath);
            mark_1280_image = ImageIO.read(file);
            file = new File(mark_456_PicPath);
            mark_456_image = ImageIO.read(file);
            file = new File(mark_lanmei_PicPath);
            mark_lanmei_image = ImageIO.read(file);
            file = new File(mark_xigua_PicPath);
            mark_xigua_image = ImageIO.read(file);
        } catch (Exception e) {
            throw new Exception("加载蒙版图片异常，请检查路径是否正确", e);
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
        Set<String> set = new HashSet<>();
        set.add(".png");
        set.add(".jpg");
        if (files != null) {
            for (File file : files) {
                //1.png
                if (file.isFile() && set.contains(file.getName().substring(file.getName().length() - 4))) {
                    imageFiles.add(file.getName());
                }
            }
        }
        return imageFiles;
    }

}
