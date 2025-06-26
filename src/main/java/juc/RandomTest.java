package juc;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
        Random random = new Random();
        random.nextInt(5);
    }


    public String largestMerge(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0, n = word1.length(), m = word2.length();
        while (i < n || j < m) {
            if (i < n && word1.substring(i).compareTo(word2.substring(j)) > 0) {
                sb.append(word1.charAt(i++));
            } else {
                sb.append(word2.charAt(j++));
            }
        }
        return sb.toString();
    }
}
