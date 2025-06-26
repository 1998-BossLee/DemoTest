package thread;

/**
 * @author: liyangjin
 * @create: 2025-06-26 11:13
 * @Description:
 */
public class TestThread {
    static class ThreadA implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread A " + i);
            }
        }
    }
    static class ThreadB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread B " + i);
            }
        }
    }
    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }
}


class Athread extends Thread {
    private Object lock;

    public Athread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("A启动了");
        synchronized (lock) {
            for (int i = 0; i <= 10; i++) {
                //System.out.println("A的i="+i);
                System.out.println("当前线程是：" + Thread.currentThread() + " i=" + i);
            }
        }
    }
}
