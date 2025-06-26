package future;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: liyangjin
 * @create: 2023-04-07 10:18
 * @Description:
 */
public class FutureTest {

    public static void main(String[] args) throws Exception {
        A a1 = new A();
        a1.fun();
        A a2 = new A();
        a2.fun();
        a1.fun();
    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> list = new ArrayList<>();
        for (String s : queries) {
            int i = 0, j = 0;
            boolean flag = true;
            while (i < s.length()) {
                if (j < pattern.length() && s.charAt(i) == pattern.charAt(j)) {
                    j++;
                } else if (Character.isUpperCase(s.charAt(i))) {
                    //只能插入小写字符
                    flag = false;
                }
                i++;
            }
            if (j < pattern.length()) {
                flag = false;
            }
            list.add(flag);
        }
        return list;
    }


}

interface TestInterface {
    Map<String, String> map = new HashMap<String, String>() {{
        put("name", "yangjin");
    }};
}

class A implements TestInterface {
    public void fun() {
        System.out.println(map);
        map.put("1", "2");
    }
}
