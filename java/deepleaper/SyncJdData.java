package deepleaper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: liyangjin
 * @create: 2023-09-20 09:52
 * @Description:
 */
public class SyncJdData {

    public static void main(String[] args) throws Exception{
        System.out.println(System.currentTimeMillis() / 1000);
    }

    private static void get(String s) {
        try {
            // 创建 URL 对象，指定要发送 GET 请求的 URL
            URL url = new URL(s);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 GET
            connection.setRequestMethod("GET");

            // 可以设置请求头信息，例如 User-Agent
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // 获取响应代码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 输出响应内容
            System.out.println("Response Body: " + response.toString());

            // 关闭连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
