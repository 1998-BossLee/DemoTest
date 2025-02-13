import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import javax.crypto.spec.PSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: liyangjin
 * @create: 2024-05-20 10:07
 * @Description:
 */
public class Main {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS).callTimeout(10000, TimeUnit.MILLISECONDS).build();


    public static void main(String[] args) throws Exception {


    }

    /**
     *
     */

    private static void readFile() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("/Users/liyangjin/Desktop/xingqu.json"));
            String content = new String(bytes);
            JSONObject jsonObject = JSONObject.parseObject(content);
            JSONArray jsonArray = jsonObject.getJSONObject("tbk_dg_material_recommend_response").getJSONObject("result_list").getJSONArray("map_data");
            JSONArray itemArray = new JSONArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject baseInfo  = jsonArray.getJSONObject(i).getJSONObject("item_basic_info");
                JSONObject item = new JSONObject();
                item.put("pict_url", baseInfo.getString("pict_url"));
                item.put("white_image", baseInfo.getString("white_image"));
                itemArray.add(item);
            }
            JSONArray resultArray = new JSONArray();
            JSONObject result = new JSONObject();
            result.put("items", itemArray);
            resultArray.add(result);
            System.out.println(resultArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findDirFile() {
        String dir = "/Users/liyangjin/Desktop/测试图片";
        File file = new File(dir);
        File[] files = file.listFiles();
        int cnt = 0;
        for (File f : files) {
            System.out.print(f.getName() + ",");
            cnt++;
            if (cnt == 1000) {
                System.out.println();
            }
        }
    }

    public static String getHttp(String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(String.format("HttpClient.get: code=%s", response.code()));
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String postHttp(String url, String json, Map<String, String> headers) {
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder request = new Request.Builder()
                .url(url)
                .post(body);
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(request::header);
        }
        try (Response response = client.newCall(request.build()).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
