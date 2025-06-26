//package sd;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//public class Txt2Image {
//
//    private static final Gson gson = new Gson();
//
//    private static CloseableHttpClient httpclient = HttpClients.createDefault();
//
//    static String prompt_format = "<lora:cuteGirlMix4_v10:0.6> ,<lora:japaneseDollLikeness_v10:0.2>,<lora:taiwanDollLikeness_v10:0.2>, \n" +
//            "1 woman,  beautiful detailed eyes, beautiful detailed face, (red lips:0.6), sexy, navel,\n" +
//            "${hairType}, ${smileType}, \n" +
//            "${doors}, ${env}, \n" +
//            "(full body shot, ${color} tank top, sport shorts,denim shorts, long legs: 1.8), \n" +
//            "(8k, RAW photo, best quality, extremely detailed CG, masterpiece:1.3), original, extremely detailed wallpaper,shot on a 50mm lens,background detailed, \n" +
//            "perfect lighting, professional lighting, light on face,cinematic lighting,";
//
//    static String negative_prompt = "(worst quality:2), (low quality:2), (normal quality:2), lowres, ((monochrome)), ((grayscale)), bad anatomy,DeepNegative, skin spots, acnes, skin blemishes,(fat:1.2),facing away, looking away,tilted head, lowres,bad anatomy,bad hands, missing fingers,extra digit, fewer digits,bad feet,poorly drawn hands,poorly drawn face,mutation,deformed,extra fingers,extra limbs,extra arms,extra legs,malformed limbs,fused fingers,too many fingers,long neck,cross-eyed,mutated hands,polar lowres,bad body,bad proportions,gross proportions,missing arms,missing legs,extra digit, extra arms, extra leg, extra foot,teethcroppe,signature, watermark, username,blurry,cropped,jpeg artifacts,text,error, (nipples:1.3),big Aperture, (depth of field, background blurring:2), ";
//
//    static int batch_count = 3;
//    static int sleep_ms = 500;
//
//    public static void main(String[] args) throws Exception {
//        init();
//        String txt2imgUrl = "http://172.17.11.244:7860/sdapi/v1/txt2img";
//        Random random = new Random();
//        JsonObject data = new JsonObject();
//        data.addProperty("negative_prompt", negative_prompt);
//        data.addProperty("steps", 30);
//        data.addProperty("save_images", true);
//        data.addProperty("sampler_name", "DPM++ 2M Karras");
//        data.addProperty("restore_faces", "true");
//        data.addProperty("width", 750);
//        data.addProperty("height", 1000);
//        data.addProperty("batch_count", 1);
//        data.addProperty("seed_resize_from_h", 0);
//        data.addProperty("seed_resize_from_w", 0);
//
//        //衣服只有一种，遍历颜色，随机发型，遍历环境
//        int cnt = 1;
//        for (String color : colors) {
//            String realPrompt;
//            for (String indoorEnv : indoorsEnvs) {
//                String hairType = hairTypes.get(random.nextInt(hairTypes.size()));
//                String smileType = smileTypes.get(random.nextInt(smileTypes.size()));
//                realPrompt = prompt_format.replace("${color}", color)
//                        .replace("${hairType}", hairType)
//                        .replace("${smileType}", smileType)
//                        .replace("${doors}", "indoors")
//                        .replace("${env}", indoorEnv);
//                data.addProperty("prompt", realPrompt);
//                int t = batch_count;
//                while (t-- > 0) {
//                    submitPost(txt2imgUrl, data);
//                    System.out.println(String.format("now=%s cnt=%d color=%s indoorEnv=%s", new Date(), cnt++, color, indoorEnv));
//                }
//            }
//            for (String outdoorEnv : outdoorsEnvs) {
//                String hairType = hairTypes.get(random.nextInt(hairTypes.size()));
//                String smileType = smileTypes.get(random.nextInt(smileTypes.size()));
//                realPrompt = prompt_format.replace("${color}", color)
//                        .replace("${hairType}", hairType)
//                        .replace("${smileType}", smileType)
//                        .replace("${doors}", "outdoors")
//                        .replace("${env}", outdoorEnv);
//                data.addProperty("prompt", realPrompt);
//                int t = batch_count;
//                while (t-- > 0) {
//                    submitPost(txt2imgUrl, data);
//                    System.out.println(String.format("now=%s cnt=%d color=%s outdoorEnv=%s", new Date(), cnt++, color, outdoorEnv));
//                }
//            }
//        }
//
//        //JsonObject responseJson = gson.fromJson(response, JsonObject.class);
//        //String encodedImage = responseJson.getAsJsonArray("images").get(0).getAsString();
//
//        //saveEncodedImage(encodedImage, "dog.png");
//    }
//
//    private static String submitPost(String url, JsonObject data) throws IOException {
//        try {
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setEntity(new StringEntity(data.toString()));
//            httpPost.setHeader("Content-type", "application/json");
//            CloseableHttpResponse response = httpclient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            String res =  EntityUtils.toString(entity);
//            Thread.sleep(sleep_ms);
//            return res;
//        } catch (Exception e) {
//            System.err.println(e);
//            return null;
//        }
//    }
//
//    static List<String> colors = new ArrayList<>();
//    static List<String> indoorsEnvs = new ArrayList<>();
//    static List<String> outdoorsEnvs = new ArrayList<>();
//    static List<String> hairTypes = new ArrayList<>();
//
//    static List<String> smileTypes = new ArrayList<>();
//    static List<String> hairColors = new ArrayList<>();
//
//    private static void init() {
//        //红橙黄绿蓝青紫黑白
////        colors.add("black");
////        colors.add("white");
////        colors.add("purple");
////        colors.add("cyan");
////        colors.add("blue");
//        colors.add("yellow");
//        colors.add("green");
//        colors.add("orange");
//        colors.add("red");
//
//        indoorsEnvs.add("Living room");
//        indoorsEnvs.add("Bedroom");
//        indoorsEnvs.add("Kitchen");
//        indoorsEnvs.add("Dining room");
//        indoorsEnvs.add("Study or Home office");
//        indoorsEnvs.add("Bathroom");
//        indoorsEnvs.add("Media room");
//        indoorsEnvs.add("Game room");
//        indoorsEnvs.add("Gym");
//        indoorsEnvs.add("Meeting room");
//        indoorsEnvs.add("Library");
//        indoorsEnvs.add("Art room");
//        indoorsEnvs.add("Broadcast room");
//
//
//        outdoorsEnvs.add("Beach");
//        outdoorsEnvs.add("Forest");
//        outdoorsEnvs.add("Woods");
//        outdoorsEnvs.add("Garden");
//        outdoorsEnvs.add("Urban street");
//        outdoorsEnvs.add("Park");
//        outdoorsEnvs.add("Rocky coastline");
//        outdoorsEnvs.add("Rural fields");
//        outdoorsEnvs.add("Lake");
//        outdoorsEnvs.add("City night view");
//        outdoorsEnvs.add("Harbor");
//        outdoorsEnvs.add("Grassland");
//        outdoorsEnvs.add("Rooftop");
//        outdoorsEnvs.add("Castle");
//        outdoorsEnvs.add("Waterfall");
//
//        //呆毛
//        hairTypes.add("ahoge");
//        //齐刘海
//        hairTypes.add("blunt bangs");
//        //不对称刘海
//        hairTypes.add("asymmetrical bangs");
//        //流海遮眼
//        hairTypes.add("hair over eyes");
//        //流海遮单眼
//        hairTypes.add("hair over one eye");
//        //流海分开
//        hairTypes.add("parted bangs");
//        //中分流海
//        hairTypes.add("middle Fringe");
//        //斜刘海
//        hairTypes.add("Slanted bangs");
//        //短发
//        hairTypes.add("short hair");
//        //长发
//        hairTypes.add("long hair");
//        //中长发
//        hairTypes.add("medium hair");
//        //卷发
//        hairTypes.add("curly hair");
//        //直发
//        hairTypes.add("straight hair");
//        //波浪
//        hairTypes.add("wavy hair");
//        //公主卷
//        hairTypes.add("drill hair");
//
//        //马尾
//        //hairTypes.add("ponytail");
//        //短马尾
//        //hairTypes.add("short ponytail");
//        //侧马尾
//        //hairTypes.add("side ponytail");
//        //双马尾
//        //hairTypes.add("twin tails");
//        //低马尾
//        //hairTypes.add("low ponytail");
//
//        smileTypes.add("kind smile");
//        smileTypes.add("smirk");
//        smileTypes.add("seductive smile");
//        smileTypes.add("blush");
//        smileTypes.add("embarrassed");
//
//        hairColors.add("silver hair");
//        hairColors.add("white hair");
//        hairColors.add("brown hair");
//        hairColors.add("pink hair");
//        hairColors.add("blue hair");
//        hairColors.add("grey hair");
//        hairColors.add("Gradation of hair");
//
//    }
//
//    private static void saveEncodedImage(String b64Image, String outputPath) throws IOException {
//        byte[] imageBytes = Base64.decodeBase64(b64Image);
//
//        try (FileOutputStream imageFile = new FileOutputStream(outputPath)) {
//            imageFile.write(imageBytes);
//        }
//    }
//}
//
