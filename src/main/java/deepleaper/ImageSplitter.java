package deepleaper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * @author: liyangjin
 * @create: 2023-07-11 11:13
 * @Description:
 */
public class ImageSplitter {

    // 指定原始图片文件夹路径
    static String folderPath = "/Users/liyangjin/Desktop/0姣捷的图片/四宫格分割测试";

    static String targetPath="";

    public static void main(String[] args) {

        // 获取文件夹下所有图片文件
        File folder = new File(folderPath);
        File[] imageFiles = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png"));

        // 遍历图片文件进行切割
        if (imageFiles != null) {
            targetPath = folderPath + "-target";
            File targetFolder = new File(targetPath);
            if (!targetFolder.exists() ) {
                targetFolder.mkdirs();
            }
            for (File imageFile : imageFiles) {
                splitImage(imageFile);
            }
        }
    }

    public static void splitImage(File imageFile) {
        try {
            // 读取原始图片
            BufferedImage originalImage = ImageIO.read(imageFile);

            // 计算切割后图片的宽高
            int width = originalImage.getWidth() / 2;
            int height = originalImage.getHeight() / 2;

            // 创建切割后的四张图片
            BufferedImage[] splitImages = new BufferedImage[4];

            String name = imageFile.getName();
            if (name.contains(".jpeg")) {
                name = name.substring(0, name.length()-5);
            }else {
                name = name.substring(0, name.length()-4);
            }
            System.out.println(name);

            int idx = 1;
            // 切割图片
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    splitImages[i * 2 + j] = new BufferedImage(width, height, originalImage.getType());
                    Graphics2D g = splitImages[i * 2 + j].createGraphics();
                    g.drawImage(originalImage, 0, 0, width, height, j * width, i * height, (j + 1) * width, (i + 1) * height, null);
                    g.dispose();

                    // 保存切割后的图片
                    String splitImagePath = targetPath + File.separator + (name + "-" + idx + ".png");
                    System.out.println(splitImagePath);
                    ImageIO.write(splitImages[i * 2 + j], "png", new File(splitImagePath));
                    idx++;
                }
            }

            System.out.println(imageFile.getAbsolutePath() + " 切割完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
