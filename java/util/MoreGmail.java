package util;

import java.util.*;

public class MoreGmail {

    public static void main(String[] args) {
        String email = "li1chenyao2@gmail.com";

        List<String> emails = generateVariations(email);
        for (String s : emails) {
            System.out.println(s);
        }
    }
    public static List<String> generateVariations(String email) {
        String localPart = email.split("@")[0];
        String domain = email.split("@")[1];
        // 大小写裂变
        return generateCaseVariations(localPart, domain);
    }

    // 生成大小写裂变 1
    private static List<String> generateCaseVariations(String localPart, String domain) {
        Set<String> caseVariations = new HashSet<>();
        char[] chars = localPart.toCharArray();
        List<String> list = new ArrayList<>();
        for (int length = 1; length <= chars.length; length++) { // 1 到 N 连续大写
            for (int start = 0; start <= chars.length - length; start++) {
                char[] temp = Arrays.copyOf(chars, chars.length);
                for (int i = start; i < start + length; i++) {
                    temp[i] = Character.toUpperCase(temp[i]);
                }
                String email = new String(temp) + "@" + domain;
                if (caseVariations.add(email)) {
                    list.add(email);
                }
            }
        }
        return list;
    }


}
