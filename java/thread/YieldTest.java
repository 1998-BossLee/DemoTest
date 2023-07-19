package thread;

/**
 * yield
 * 让出当前的CPU时间片，等待下一轮调用，依旧处于就绪状态。
 */
public class YieldTest {
    public static void main(String[] args) {
        new YieldThread();
        new YieldThread();
        new YieldThread();
        new YieldThread();
    }

}

class YieldThread implements Runnable {

    public YieldThread() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread() + " yield cpu...");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + " end...");
    }
}
