package webauto;

import java.util.*;

/**
 * @author: liyangjin
 * @create: 2025-03-03 18:12
 * @Description:
 */
public class MonadTest {

    static Integer[] task_ids = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        List<Account> accountList = new ArrayList<>();
        Collections.shuffle(accountList);
        List<Integer> taskIds = new ArrayList<>(Arrays.asList(task_ids));
        Collections.shuffle(taskIds);

        for (Account account : accountList) {
            //todo 移动鼠标把页面点开

            for (int taskId : taskIds) {
                switch (taskId) {
                    case 1:
                    case 2:
                        break;
                }
            }
        }

    }

    Map<Integer, String> talentumUrlMap = new HashMap<Integer, String>() {
        {
            put(1, "https://monad.talentum.id/tasks/task/2739");
            put(2, "https://monad.talentum.id/tasks/task/2797");
            put(3, "https://monad.talentum.id/tasks/task/2745");
            put(4, "https://monad.talentum.id/tasks/task/2750");
            put(5, "https://monad.talentum.id/tasks/task/2760");
            put(6, "https://monad.talentum.id/tasks/task/2779");
            put(7, "https://monad.talentum.id/tasks/task/2812");
            put(8, "https://monad.talentum.id/tasks/task/2813");
            put(9, "https://monad.talentum.id/tasks/task/2857");
            put(10, "https://monad.talentum.id/tasks/task/2838");
            put(11, "https://monad.talentum.id/tasks/task/2778");
        }
    };

    /**
     * 前提条件，先把账号手动连接钱包，输入邀请码
     * 【输入地址】回车，【start work】，【Mint】，【签名确认】，【Verify】，【claim Reward】
     */
    private void talentum(int taskId) {
        String url = talentumUrlMap.get(taskId);


    }

    //签到

    //领水

    //买币
    private void swap() {

    }

    //质押


    //mint NFT


    //添加流动对


}
