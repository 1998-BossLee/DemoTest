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

    public static void main(String[] args) throws Exception {
        String path = "/Users/liyangjin/Desktop/0姣捷的图片/剪辑脚本需要/target/1的副本.png";
        File file = new File(path);
        System.out.println(file.canRead());
        BufferedImage image = ImageIO.read(file);
    }

}
