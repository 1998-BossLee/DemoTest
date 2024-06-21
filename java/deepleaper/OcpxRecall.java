package deepleaper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: liyangjin
 * @create: 2024-02-20 11:21
 * @Description:
 */
public class OcpxRecall {

    public static void main(String[] args) {
        String csvFilePath = "/Users/liyangjin/Desktop/3月19-20日ocpx补数-mi.csv";
        List<String> uris = readAndProcessCsv(csvFilePath);

        ExecutorService executorService = Executors.newFixedThreadPool(15);

        int cnt = 0;
        // 将任务提交给线程池
        for (String uri : uris) {
            //System.out.println(uri);
            executorService.submit(() -> sendGetRequest(uri));
        }

        // 关闭线程池
        executorService.shutdown();
    }

    private static List<String> readAndProcessCsv(String filePath) {
        List<String> uris = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming CSV is comma-separated, split by commas
                String[] columns = line.split(",");
                if (columns.length > 0) {
                    // Add the first column (URI) to the list
                    uris.add("https://publisher-api-mini.deepleaper.com" + columns[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uris;
    }

    private static void sendGetRequest(String uri) {
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Handle the response if needed
            int responseCode = connection.getResponseCode();
            System.out.println("GET request to " + uri + " - Response Code: " + responseCode);

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
