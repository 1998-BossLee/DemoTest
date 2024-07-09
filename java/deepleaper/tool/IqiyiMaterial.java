//package deepleaper.tool;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URLEncoder;
//
//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
//
///**
// * @author: liyangjin
// * @create: 2024-06-24 17:29
// * @Description:
// */
//public class IqiyiMaterial {
//
//    public static void main(String[] args) {
//
//        String uploadUrl = "http://IP/upload/post";
//        String dspToken = "";
//        String fileName = "test.mp4";
//        Integer adType = 11;
//        Integer isPmp = 1;
//        String platform = "2";
//        String clickUrl = "http://";
//        Long adId = 1000000000L;
//        String videoId = "123456789";
//        String appleId = "123456789";
//        Integer creativeMode = 2;
//        Integer interactiveStyle = 0;
//        Integer adMaterialType = 0;
//        Integer displayStyle = 0;
//        String clickTitle = "click_title_test ";
//        String clickDescription = "clickDescriptionTEST";
//
//        DefaultHttpClient client = new DefaultHttpClient();
//        try {
//            HttpPost post = new HttpPost(uploadUrl);
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
////开机屏需上传多个素材文件，文件个数、顺序及其意义如上php范例所示
//            builder.addBinaryBody("pic1", new File("e:\\SQL\\7201096-andriod.jpg"), ContentType.MULTIPART_FORM_DATA, "7201096-andriod.jpg");
//            builder.addBinaryBody("pic2", new File("e:\\SQL\\10801850-android.jpg"), ContentType.MULTIPART_FORM_DATA, "10801850-android.jpg");
//            ContentType.MULTIPART_FORM_DATA, fileName);
//            HttpEntity multipart = builder.build();
//            post.setEntity(multipart);
//            post.addHeader("dsp_token", dspToken);
//            post.addHeader("file_name", URLEncoder.encode(fileName, "UTF-8"));
//            post.addHeader("ad_type", String.valueOf(adType));
//            post.addHeader("is_pmp", String.valueOf(isPmp));
//            post.addHeader("apple_id", appleId);
//            post.addHeader("creative_mode", String.valueOf(creativeMode));
//            post.addHeader("ad_type", String.valueOf(adType));
//            post.addHeader("platform", platform);
//            post.addHeader("click_url", URLEncoder.encode(clickUrl, "UTF-8"));
//            post.addHeader("ad_id", String.valueOf(adId));
//            post.addHeader("video_id", videoId);
//            post.addHeader("interactive_style ", interactiveStyle);
//            post.addHeader("ad_material_type", adMaterialType);
//            post.addHeader("display_style", displayStyle);
//            post.addHeader("click_title", URLEncoder.encode(clickTitle, "UTF-8"));
//            post.addHeader("click_description", URLEncoder.encode(clickDescription, "UTF-8")
//            try {
//                HttpResponse response = client.execute(post);
//                int status = response.getStatusLine().getStatusCode();
//                log.info("material_status ->" + status)
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                post.releaseConnection();
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}
