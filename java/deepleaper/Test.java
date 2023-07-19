package deepleaper;

/**
 * @author: liyangjin
 * @create: 2023-07-10 15:09
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        String s = "{\n" +
                "    \"id\": \"5f91b952-fa77-421e-b5f5-62a806b60306\",\n" +
                "    \"device\": {\n" +
                "        \"carrier\": \"46000\",\n" +
                "        \"mac\": \"00:00:00:00:00:00\",\n" +
                "        \"boot_mark\": \"392aa4ad-1e22-4479-8e38-27b6a7890769\",\n" +
                "        \"ua\": \"Mozilla/5.0 (Linux; Android 10; TAS-AN00 Build/HUAWEITAS-AN00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/88.0.4324.93 Mobile Safari/537.36\",\n" +
                "        \"ip\": \"112.27.254.9\",\n" +
                "        \"model\": \"HUAWEITAS-AN00\",\n" +
                "        \"os\": \"Android\",\n" +
                "        \"oaid\": \"5b4ad7eb-656b-4328-9577-55365a756747\",\n" +
                "        \"androidid\": \"993b15d8ca09c123\",\n" +
                "        \"make\": \"HUAWEI\",\n" +
                "        \"update_mark\": \"1591995623.990000000\",\n" +
                "        \"devicetype\": 2,\n" +
                "        \"w\": \"1080\",\n" +
                "        \"connectiontype\": 2,\n" +
                "        \"appstore_ver\": \"120201301\",\n" +
                "        \"hmscore\": \"60501302\",\n" +
                "        \"ext\": \"1000000101100000000001000000011100001000010111111001111111\",\n" +
                "        \"osv\": \"10\",\n" +
                "        \"h\": \"2259\",\n" +
                "        \"ppi\": \"480\",\n" +
                "        \"oaid_md5\": \"a3352c99c1795f77007248cca509f57d\",\n" +
                "        \"orientation\": \"0\"\n" +
                "    },\n" +
                "    \"app\": {\n" +
                "        \"name\": \"大学搜题酱\",\n" +
                "        \"appid\": \"105926\",\n" +
                "        \"bundle\": \"com.zmzx.college.search\",\n" +
                "        \"ver\": \"1.11.2\",\n" +
                "        \"id\": \"105926\"\n" +
                "    },\n" +
                "    \"test\": 0,\n" +
                "    \"user\": {},\n" +
                "    \"tmax\": 360,\n" +
                "\n" +
                "    \"imp\": [\n" +
                "        {\n" +
                "            \"id\": \"1\",\n" +
                "            \"type\": 1,\n" +
                "            \"support_down\": 1,\n" +
                "            \"banner\": {\n" +
                "                \"mimes\": [\n" +
                "                    \"jpg\",\n" +
                "                    \"png\",\n" +
                "                    \"gif\",\n" +
                "                    \"mp4\",\n" +
                "                    \"jpeg\"\n" +
                "                ],\n" +
                "                \"id\": 1,\n" +
                "                \"w\": 720,\n" +
                "                \"h\": 1280\n" +
                "            },\n" +
                "            \"support_wx\": 0,\n" +
                "            \"pid\": \"1035989\",\n" +
                "            \"support_dp\": 1,\n" +
                "            \"ext\": {\n" +
                "                \"atype\": 4\n" +
                "            },\n" +
                "            \"bidfloor\": 0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"secure\": 0\n" +
                "}";
        System.out.println(s.getBytes().length);
    }
}
