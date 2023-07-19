package deepleaper;

import java.io.File;

/**
 * @author: liyangjin
 * @create: 2023-07-03 22:44
 * @Description:
 */
public class FileRename {


    public static void main(String[] args) {
        String folderPath = "/Users/liyangjin/Desktop/0图片/二次元/80-皮卡丘";
        File folder = new File(folderPath);
        int name = 1;
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".png")) {
                        String newName = name + ".png";
                        File newFile = new File(folderPath, newName);
                        if (file.renameTo(newFile)) {
                            System.out.println("Renamed file: " + file.getName() + " to " + newFile.getName());
                        } else {
                            System.out.println("Failed to rename file: " + file.getName());
                        }
                        name++;
                    }
                }
            }
        }
    }



}
