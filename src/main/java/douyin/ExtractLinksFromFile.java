package douyin;


import java.io.*;
import java.util.regex.*;
import java.util.*;

public class ExtractLinksFromFile {

    public static void main(String[] args) {
        String filePath = "your_file_path.txt"; // 替换为你的文件路径
        List<String> links = extractLinksFromFile(filePath);

        for (String link : links) {
            System.out.println(link);
        }
    }

    public static List<String> extractLinksFromFile(String filePath) {
        List<String> links = new ArrayList<>();
        String regex = "https://v\\.douyin\\.com/\\S+"; // 匹配以 https://v.douyin.com/ 开头的链接

        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    links.add(matcher.group());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return links;
    }
}
