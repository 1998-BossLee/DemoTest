package util;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: liyangjin
 * @create: 2025-03-03 20:31
 * @Description: 启动后等待 3 秒，然后打印鼠标的绝对坐标
 */
public class FindCoordinate {

    public static void main(String[] args) {
        try {
            System.out.println("请在 3 秒内将鼠标移动到目标位置...");
            TimeUnit.SECONDS.sleep(3); // 等待 3 秒

            // 获取鼠标位置
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            Point point = pointerInfo.getLocation();

            // 打印鼠标的 x 和 y 坐标
            System.out.println("鼠标当前位置: X=" + point.x + ", Y=" + point.y);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
