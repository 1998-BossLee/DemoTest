package deepleaper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyangjin
 * @create: 2023-08-18 10:29
 * @Description: 给定文件夹和宽高，自动从中心裁剪。
 */
public class ImageCropper {

    static String dirPath = "/Users/liyangjin/IdeaProject/MiniDsp/temp/test";

    static String targetDirPath;

    //名字里的唯一关键词， 宽、高。不要颠倒。
    static Map<String, Node> map = new HashMap<String, Node>() {
        {
            put("1080", new Node(1080, 1920));
            //put("1280", new Node(720, 1280));
        }
    };


    public static void main(String[] args) {
        // 获取文件夹下所有图片文件
        File folder = new File(dirPath);
        File[] imageFiles = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png"));
        // 遍历图片文件进行裁剪
        if (imageFiles != null) {
            targetDirPath = dirPath + "-target";
            File targetFolder = new File(targetDirPath);
            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }
            for (File imageFile : imageFiles) {
                String name = imageFile.getName();
                for (Map.Entry<String, Node> entry : map.entrySet()) {
                    if (!name.contains(entry.getKey())) {
                        Node node = entry.getValue();
                        int gcd = gcd(node.w, node.h);
                        crop(imageFile, node.w / gcd, node.h / gcd, node);
                    }
                }
            }
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    private static void crop(File imageFile, int w, int h, Node node) {
        try {
            // 读取原始图片
            BufferedImage originalImage = ImageIO.read(imageFile);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            int tw, th, sx, sy, ex, ey;
            if (w * height == h * width) {
                //不用裁剪
                String targetPicPath = targetDirPath + File.separator + imageFile.getName();
                ImageIO.write(originalImage, "png", new File(targetPicPath));
                System.out.println(imageFile.getAbsolutePath() + " 无需裁剪");
                return;
            } else if (w * height < h * width) {
                //高固定
                th = (height % h == 0) ? height : (height - height % h);
                tw = th / h * w;
                sx = (width - tw) / 2;
                sy = 0;
                ex = sx + tw;
                ey = th;
            } else {
                //宽固定
                tw = (width % w == 0) ? width : (width - width % w);
                th = tw / w * h;
                sx = 0;
                sy = (height - th) / 2;
                ex = tw;
                ey = sy + th;
            }
            BufferedImage targetImage = new BufferedImage(tw, th, originalImage.getType());
            Graphics2D g = targetImage.createGraphics();
            g.drawImage(originalImage, 0, 0, tw, th, sx, sy, ex, ey, null);
            g.dispose();
            BufferedImage resizedImage = new BufferedImage(node.w, node.h, originalImage.getType());
            Graphics2D g2 = resizedImage.createGraphics();
            g2.drawImage(targetImage, 0, 0, node.w, node.h, null);
            g2.dispose();
            String targetPicPath = targetDirPath + File.separator + imageFile.getName();
            ImageIO.write(resizedImage, "png", new File(targetPicPath));
            System.out.println(imageFile.getAbsolutePath() + " 裁剪完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

class Node {
    int w;
    int h;
    public Node(int w, int h) {
        this.w = w;
        this.h = h;
    }
}
