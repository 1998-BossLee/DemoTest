package web3;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MouseMoveExample {

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            // 获取当前鼠标位置
            Point currentPos = MouseInfo.getPointerInfo().getLocation();

            //1、数字 1345 570
            int targetX = 40, targetY = 40;
            for (int i = 1; i <= 1; i++) {
                moveToAndClick(robot, targetX, targetY);
                System.out.println("第" + i + "次对焦成功");
                //            robot.delay(500);
//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//            robot.delay(500);
//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//            robot.delay(500);
//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            }


            //pressDelete(robot);
            String[] s = {"0.0000198", "0.00000199", "0.00009989", "0.0009898", "0.000098799", "0.000001", "0.00000111", "0.00000123"};
//            typeString(robot, s[random.nextInt(1000)%8]);
            //typeString(robot, "0.00001");

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private static void moveToAndClick(Robot robot, int x, int y) throws Exception {
        move(robot, x, y);
        robot.mouseMove(x, y);
        robot.delay(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private static void pressDelete(Robot robot) {
        int times = 40;
        for (int i = 0; i < times; i++) {
            robot.delay(500); // 等待一段时间，可以调整这个值以控制按键速度
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);

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
}