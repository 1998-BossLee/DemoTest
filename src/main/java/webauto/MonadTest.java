package webauto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author: liyangjin
 * @create: 2025-03-03 18:12
 * @Description:
 */
public class MonadTest {

    static Integer[] task_ids = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        String s = "2734,2917,2906,2918,2923,2927,2931,2956,2837,2932,2815,2816,2957";
        String[] taskIds = s.split(",");
        for (int i = 0; i < taskIds.length; i++) {
            System.out.println("\"https://monad.talentum.id/tasks/task/" + taskIds[i] +"\",");
        }
    }




}
