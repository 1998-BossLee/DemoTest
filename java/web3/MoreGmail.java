package web3;

import java.util.*;

public class MoreGmail {

    public static void main(String[] args) {
        String email = "guanlaoshu1@gmail.com";
        int plusVariationCount = 20; // 生成 5 个+裂变

        Set<String> variations = generateVariations(email, plusVariationCount);

        for (String variant : variations) {
            System.out.println(variant);
        }
    }

    public static Set<String> generateVariations(String email, int plusVariationCount) {
        Set<String> variations = new HashSet<>();

        String localPart = email.split("@")[0];
        String domain = email.split("@")[1];

        // 大小写裂变
        //variations.addAll(generateCaseVariations(localPart, domain));

        // +裂变
        variations.addAll(generatePlusVariations(localPart, domain, plusVariationCount));

        return variations;
    }

    // 生成大小写裂变
    private static Set<String> generateCaseVariations(String localPart, String domain) {
        Set<String> caseVariations = new HashSet<>();
        char[] chars = localPart.toCharArray();

        for (int length = 1; length <= chars.length; length++) { // 1 到 N 连续大写
            for (int start = 0; start <= chars.length - length; start++) {
                char[] temp = Arrays.copyOf(chars, chars.length);
                for (int i = start; i < start + length; i++) {
                    temp[i] = Character.toUpperCase(temp[i]);
                }
                caseVariations.add(new String(temp) + "@" + domain);
            }
        }

        return caseVariations;
    }

    // 生成 + 裂变
    private static Set<String> generatePlusVariations(String localPart, String domain, int count) {
        Set<String> plusVariations = new HashSet<>();
        Random random = new Random();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < count; i++) {
            StringBuilder plusSuffix = new StringBuilder("+");
            for (int j = 0; j < 5; j++) { // 生成 5 个随机字符
                plusSuffix.append(chars.charAt(random.nextInt(chars.length())));
            }
            plusVariations.add(localPart + plusSuffix + "@" + domain);
        }

        return plusVariations;
    }
}
