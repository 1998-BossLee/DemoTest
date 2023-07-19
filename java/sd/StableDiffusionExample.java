package sd;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;

public class StableDiffusionExample {

    private static final Gson gson = new Gson();

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {
        String txt2imgUrl = "http://127.0.0.1:7860/sdapi/v1/txt2img";
        JsonObject data = new JsonObject();
        data.addProperty("prompt", "a dog wearing a hat");

        String response = submitPost(txt2imgUrl, data);
        JsonObject responseJson = gson.fromJson(response, JsonObject.class);
        String encodedImage = responseJson.getAsJsonArray("images").get(0).getAsString();

        saveEncodedImage(encodedImage, "dog.png");
    }

    private static String submitPost(String url, JsonObject data) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(data.toString()));
        httpPost.setHeader("Content-type", "application/json");

        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }
    }

    private static void saveEncodedImage(String b64Image, String outputPath) throws IOException {
        byte[] imageBytes = Base64.decodeBase64(b64Image);

        try (FileOutputStream imageFile = new FileOutputStream(outputPath)) {
            imageFile.write(imageBytes);
        }
    }
}

