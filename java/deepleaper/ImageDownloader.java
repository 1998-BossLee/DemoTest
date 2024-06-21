package deepleaper;


import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author: liyangjin
 * @create: 2023-07-10 15:09
 * @Description:
 */
public class ImageDownloader {

    public static void main(String[] args) throws Exception {
        // 创建一个HTTP服务器，监听端口8061
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8061), 0);
        // 创建一个处理器，用于处理下载图片的请求
        server.createContext("/download-image", new ImageHandler());

        // 启动HTTP服务器
        server.start();
        System.out.println("Server is listening on port 8061...");
    }

    static class ImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                // 如果是POST请求，从请求体中获取参数
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    requestBody.append(line);
                }
                br.close();

                // 解析请求体中的JSON参数
                String imageUrl = parseImageUrlFromJson(requestBody.toString());
                System.out.println(imageUrl);
                try {
                    String base64 = downloadImage(imageUrl);
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, base64.length());

                    OutputStream os = exchange.getResponseBody();
                    os.write(base64.getBytes());
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    exchange.sendResponseHeaders(500, 0); // 发生错误时返回500
                }
            } else {
                // 如果不是POST请求，返回405 Method Not Allowed
                exchange.sendResponseHeaders(405, 0);
                exchange.getResponseBody().close();
            }
        }

        private String parseImageUrlFromJson(String jsonString) {
            try {
                // 解析JSON参数
                jsonString = jsonString.replaceAll("\\s", "");;
                if (jsonString.startsWith("{\"url\":\"") && jsonString.endsWith("\"}")) {
                    // 提取"url"的值
                    int startIndex = "{\"url\":\"".length();
                    int endIndex = jsonString.length() - "\"}".length();
                    return jsonString.substring(startIndex, endIndex);
                } else {
                    // 如果字符串格式不正确，返回null或抛出异常
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static String downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStream inputStream = connection.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        byte[] imageBytes = outputStream.toByteArray();
        outputStream.close();

        // 使用 Base64 编码将图片转换为 Base64 字符串
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        //System.out.println(base64Image);
        return base64Image;
    }
}
