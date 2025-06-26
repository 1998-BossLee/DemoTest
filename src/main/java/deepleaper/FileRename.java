package deepleaper;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: liyangjin
 * @create: 2023-07-03 22:44
 * @Description:
 */
public class FileRename {

    //样式数量。 前n张，前缀是1-n。 第n+1到2n张，前缀是1-n。往复循环。
    static int n = 2;
    static String folderPath = "/Users/liyangjin/Desktop/0姣捷的图片/加前缀测试";

    public static void main(String[] args) {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                List<String> sortNames = Arrays.stream(files).filter(it ->
                        it.getName().endsWith(".png") || it.getName().endsWith(".jpg")
                ).map(File::getName).sorted().collect(Collectors.toList());
                for (int i = 0; i < sortNames.size(); i++) {
                    File file = new File(folderPath + File.separator + sortNames.get(i));
                    String newName = String.format("%03d", i / n) + "_" + sortNames.get(i);
                    File newFile = new File(folderPath, newName);
                    if (file.renameTo(newFile)) {
                        System.out.println("Renamed file: " + file.getName() + " to " + newFile.getName());
                    } else {
                        System.out.println("Failed to rename file: " + file.getName());
                    }
                }
            }
        }
    }
}
