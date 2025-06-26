package mj;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: liyangjin
 * @create: 2023-06-19 10:00
 * @Description:
 */
public class LoadImage {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<String> urls = new ArrayList<>();
        while (n-- > 0) {
            String url = scan.next();
            urls.add(url);
        }
        String saveDirPath = "/Users/liyangjin/Desktop/0姣捷的图片/程序下载图/";
        for (int i = 0; i < urls.size(); i++) {
            String imageUrl = urls.get(i);
            try {
                String picPath = saveDirPath + (i + 1) + ".png";
                downloadImage(imageUrl, picPath);
                System.out.println("图片下载成功");
            } catch (IOException e) {
                System.err.println("图片下载失败：imageUrl=" + imageUrl);
                e.printStackTrace();
            }
        }
    }

    public static void downloadImage(String imageUrl, String savePath) throws IOException {
        URL url = new URL(imageUrl);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStream inputStream = connection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }
        fileOutputStream.close();
        bufferedInputStream.close();
    }

}
