package deepleaper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

/**
 * @author: liyangjin
 * @create: 2023-07-14 14:11
 * @Description: 4宫格拼接
 * 输入一个文件夹，图片输出到另一个文件夹。
 * 每张图片只能1、2、3、4为止各出现一次。
 * 1、左上 2、右上 3、左下 4、右下
 */
public class FourPicJoin {

    static String dirPath = "/Users/liyangjin/Desktop/0姣捷的图片/四宫格拼接测试";

    static String targetDirPath;

    public static void main(String[] args) throws Exception {
        checkDirPath(dirPath);
        targetDirPath = dirPath + "-target";
        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        List<String> picNames = getValidImageFiles(dirPath);
        if (picNames.size() < 4) {
            throw new Exception("图片数量少于4张");
        }
        widthHeightEqual(picNames);
        Map<String, Set<Integer>> map = new HashMap<>();
        Set<String> groups = new HashSet<>();
        int n = picNames.size();
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                for (int c = 0; c < n; c++) {
                    for (int d = 0; d < n; d++) {
                        String pic1 = picNames.get(a);
                        String pic2 = picNames.get(b);
                        String pic3 = picNames.get(c);
                        String pic4 = picNames.get(d);
                        //4张不同的图
                        Set<String> set = new HashSet<>();
                        if (!(set.add(pic1) && set.add(pic2) && set.add(pic3) && set.add(pic4))) {
                            continue;
                        }
                        //这4张图没有组合过，需要排序
                        String[] arr = new String[]{pic1, pic2, pic3, pic4};
                        Arrays.sort(arr);
                        String targetName = String.format("%s_%s_%s_%s.png", arr[0].substring(0, arr[0].length() - 4),
                                arr[1].substring(0, arr[1].length() - 4), arr[2].substring(0, arr[2].length() - 4), arr[3].substring(0, arr[3].length() - 4));
                        if (groups.contains(targetName)) {
                            continue;
                        }
                        //每张图只能在1、2、3、4位置出现1次。
                        Set<Integer> set1 = map.getOrDefault(pic1, new HashSet<>());
                        Set<Integer> set2 = map.getOrDefault(pic2, new HashSet<>());
                        Set<Integer> set3 = map.getOrDefault(pic3, new HashSet<>());
                        Set<Integer> set4 = map.getOrDefault(pic4, new HashSet<>());
                        if (set1.contains(1) || set2.contains(2) || set3.contains(3) || set4.contains(4)) {
                            continue;
                        }
                        if (!join(pic1, pic2, pic3, pic4)) {
                            continue;
                        }
                        set1.add(1);
                        map.put(pic1, set1);
                        set2.add(2);
                        map.put(pic2, set2);
                        set3.add(3);
                        map.put(pic3, set3);
                        set4.add(4);
                        map.put(pic4, set4);
                        groups.add(targetName);
                    }
                }
            }
        }
    }

    private static boolean join(String pic1, String pic2, String pic3, String pic4) {
        try {
            File file = new File(dirPath + File.separator + pic1);
            BufferedImage image1 = ImageIO.read(file);
            file = new File(dirPath + File.separator + pic2);
            BufferedImage image2 = ImageIO.read(file);
            file = new File(dirPath + File.separator + pic3);
            BufferedImage image3 = ImageIO.read(file);
            file = new File(dirPath + File.separator + pic4);
            BufferedImage image4 = ImageIO.read(file);
            int width = image1.getWidth(), height = image1.getHeight();
            BufferedImage targetImage = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_INT_RGB);
            targetImage.getGraphics().drawImage(image1, 0, 0, null);
            targetImage.getGraphics().drawImage(image2, width, 0, null);
            targetImage.getGraphics().drawImage(image3, 0, height, null);
            targetImage.getGraphics().drawImage(image4, width, height, null);
            String targetName = String.format("%s_%s_%s_%s.png", pic1.substring(0, pic1.length() - 4),
                    pic2.substring(0, pic2.length() - 4), pic3.substring(0, pic3.length() - 4), pic4.substring(0, pic4.length() - 4));
            String targetImagePath = targetDirPath + File.separator + targetName;
            ImageIO.write(targetImage, "png", new File(targetImagePath));
            System.out.println(String.format("图片拼接成功 pic1=%s pic2=%s pic3=%s pic4=%s targetName=%s", pic1, pic2, pic3, pic4, targetName));
            return true;
        } catch (Exception e) {
            System.err.println(String.format("图片拼接失败 pic1=%s pic2=%s pic3=%s pic4=%s", pic1, pic2, pic3, pic4));
            return false;
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
                if (file.isFile() && (file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase().endsWith(".jpg"))) {
                    imageFiles.add(file.getName());
                }
            }
        }
        return imageFiles;
    }

    private static void widthHeightEqual(List<String> picNames) throws Exception {
        File firstFile = new File(dirPath + File.separator + picNames.get(0));
        BufferedImage firstImage = ImageIO.read(firstFile);
        for (int i = 0; i < picNames.size(); i++) {
            File file = new File(dirPath + File.separator + picNames.get(i));
            BufferedImage image = ImageIO.read(file);
            if (firstImage.getWidth() != image.getWidth() || firstImage.getHeight() != image.getHeight()) {
                throw new Exception("有图片宽高不一致");
            }
        }
    }
}
