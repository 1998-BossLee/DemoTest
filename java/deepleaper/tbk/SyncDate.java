//package deepleaper.tbk;
//
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.lang.Pair;
//import com.google.gson.JsonObject;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.LinkedList;
//
///**
// * @author: liyangjin
// * @create: 2023-08-16 14:17
// * @Description:
// */
//public class SyncDate {
//
//    private static CloseableHttpClient httpclient = HttpClients.createDefault();
//
//    private static String url = "https://mini-dsp-api.deepleaper.com/tbk/order/sync-order-data";
//
//    private static LinkedList<Pair<String, String>> failList = new LinkedList<>();
//    static String key = "27996601";
//
//    public static void main(String[] args) throws Exception {
//
//        Date startTime = DateUtil.parseDateTime("2023-07-01 00:00:00");
//        Date endTime = DateUtil.parseDateTime("2023-09-04 23:59:59");
//
//        String s = "", e = "";
//        while (!s.equals("2023-09-05 00:00:00")) {
//            System.out.println(new Date());
//            s = DateUtil.format(startTime, "yyyy-MM-dd HH:mm:ss");
//            e = DateUtil.format(endTime, "yyyy-MM-dd HH:mm:ss");
//            JsonObject body = new JsonObject();
//            body.addProperty("timeType", "tkCreateTime");
//            body.addProperty("startTime", s);
//            body.addProperty("endTime", e);
//            startTime = DateUtil.offsetDay(startTime, 1);
//            endTime = DateUtil.offsetDay(endTime, 1);
//            String res = submitPost(url, body);
//            if (res == null || !res.contains("\"code\":200")) {
//                failList.add(new Pair<>(s, e));
//                System.err.println("s=" + s + " e=" + e + " fail");
//            } else {
//                System.out.println("s=" + s + " e=" + e + " success");
//            }
//        }
//        while (failList.size() > 0) {
//            Pair<String, String> top = failList.removeFirst();
//            JsonObject body = new JsonObject();
//            s = top.getKey();
//            e = top.getValue();
//            body.addProperty("startTime", s);
//            body.addProperty("endTime", e);
//            String res = submitPost(url, body);
//            if (res == null || !res.contains("\"code\":200")) {
//                failList.add(new Pair<>(s, e));
//                System.err.println("s=" + s + " e=" + e + " fail");
//            } else {
//                System.out.println("s=" + s + " e=" + e + " success");
//            }
//        }
//    }
//
//    private static String submitPost(String url, JsonObject data) throws IOException {
//        try {
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setEntity(new StringEntity(data.toString()));
//            httpPost.setHeader("Content-type", "application/json");
//            CloseableHttpResponse response = httpclient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            String res = EntityUtils.toString(entity);
//            Thread.sleep(3000);
//            System.out.println(res);
//            return res;
//        } catch (Exception e) {
//            System.err.println(e);
//            return null;
//        }
//    }
//
//}
