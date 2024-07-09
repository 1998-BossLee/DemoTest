package web3;


import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;

/**
 * @author: yangjin
 * @create: 2024-05-16 19:03
 * @Description:
 */
public class ParticleNetwork {

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        Random random = new Random();
        String[] s = {"0.0000198", "0.00000199", "0.00009989", "0.0009898", "0.000098799", "0.000001", "0.00000111", "0.00000123"};
        int t = 0;
        //每次要转的币数量
        String amount = "0.00000001";
        while (++t<=47) {
            // 1. 给出【send】点击坐标，点击2下
            moveToAndClick(robot, 1350, 499);
            robot.delay(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(1000);

            // 2. 鼠标移动到 【最近钱包地址】里，点击2下
            moveToAndClick(robot, 1350, 475);
            robot.delay(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(1000);

            // 3. 鼠标移动到金额输入框坐标停留，点击，【Back】删除金额，重新输入金额
            // moveToAndClick(robot, 1345, 670);
            // moveToAndClick(robot, 1345, 670);
            robot.delay(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(500);
            pressDelete(robot); //【Back】
            //amount = s[random.nextInt(1000)%s.length];//打开之后从s里随机拿一个金额
            typeString(robot, amount);
            Thread.sleep(1000);

            // 4. 鼠标移动到下面【send】按钮，点击
            clickAt(robot, 1400, 900);

            // 5. 停留10-15秒，再次点击
            robot.delay(randomDelay(10000, 15000));
            clickAt(robot, 1400, 900);

            // 6. 停留10-15秒，移动到【验证框】坐标，点击
            robot.delay(randomDelay(10000, 15000));
            moveToAndClick(robot, 1400, 820);
            robot.delay(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // 7. 停留10-15秒，移动到【签名】按钮区域，点击。不同钱包坐标有偏移
            robot.delay(randomDelay(10000, 15000));
             moveToAndClick(robot, 1800, 610); //小狐狸
            //moveToAndClick(robot, 1800, 710); //okx
            robot.delay(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // 8. 停留5-10秒，移动到【X】按钮，叉掉弹窗
            robot.delay(randomDelay(10000, 15000));
            moveToAndClick(robot, 1750, 690);

            // 9. 随机停留5-20妙，开始下一次循环
            robot.delay(randomDelay(10000, 20000));
            System.out.println(t + " " + new Date());

            // 10、第一个号执行50次之后，切换转币数量用于下一个号，停留5分钟，关闭当前窗口。
            if (t == 99) {
                amount = "0.00000009";
                //关闭当前窗口
                closeWindow(robot, 42, 40);
                Thread.sleep(5 * 60 * 1000);
            }
            // 11. 第二个号也执行50次之后，
            if (t == 100) {
                amount = "0.00000008";
                //关闭当前窗口
                closeWindow(robot, 42, 40);
                Thread.sleep(5 * 60 * 1000);
            }

        }
        //closeWindow(robot, 42, 40);
    }

    private static void closeWindow(Robot robot, int x, int y) throws Exception{
        moveToAndClick(robot, 25, 40);
    }

    private static void clickAt(Robot robot, int x, int y) {
        robot.mouseMove(x, y);
        robot.delay(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private static void moveToAndClick(Robot robot, int x, int y) throws Exception{
        move(robot, x, y);
        robot.mouseMove(x, y);
        robot.delay(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private static void pressDelete(Robot robot) {
        int times = 10;
        for (int i = 0; i < times; i++) {
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
            robot.delay(100); // 等待一段时间，可以调整这个值以控制按键速度
        }
    }

    private static void typeString(Robot robot, String input) {
        for (char c : input.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            robot.delay(50);
        }
    }



    private static int randomDelay(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    private static void move(Robot robot, int targetX, int targetY) throws Exception {
        Point currentPos = MouseInfo.getPointerInfo().getLocation();
        // 设置移动步长
        int step = 10;

        // 计算移动的总距离
        double distance = currentPos.distance(targetX, targetY);
        // 计算移动的总步数
        int totalSteps = (int) (distance / step);
        // 计算每步移动的增量
        double deltaX = (targetX - currentPos.x) / (double) totalSteps;
        double deltaY = (targetY - currentPos.y) / (double) totalSteps;

        // 模拟缓慢移动
        for (int i = 0; i < totalSteps; i++) {
            currentPos.translate((int) deltaX, (int) deltaY);
            robot.mouseMove(currentPos.x, currentPos.y);
            Thread.sleep(10); // 控制移动的速度，单位为毫秒
        }
    }

}
