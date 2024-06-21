package web3;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ScrollPage {
    public static void main(String[] args) {
        try {
            // 创建Robot实例
            Robot robot = new Robot();

            // 设置延迟时间，确保页面完全加载
            robot.delay(2000);

            // 向下滚动页面，每次滚动3个单位，共滚动10次
            for (int i = 0; i < 10; i++) {
                robot.mouseWheel(3);
                robot.delay(500); // 每次滚动后延迟500毫秒
            }

            // 向上滚动页面，每次滚动3个单位，共滚动10次
            for (int i = 0; i < 10; i++) {
                robot.mouseWheel(-3);
                robot.delay(500); // 每次滚动后延迟500毫秒
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

