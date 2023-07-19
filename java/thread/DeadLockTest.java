package thread;

/**
 * 测试wait方法：对象调用wait只释放当前锁，获取到的其他锁不会释放。
 */
public class DeadLockTest {

    private static volatile Object lockA = new Object();
    private static volatile Object lockB = new Object();

    public static void main(String[] args) throws Exception {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lockA) {
                        System.out.println("threadA get lockA");
                        synchronized (lockB) {
                            System.out.println("threadA get lockB");
                            System.out.println("threadA release lockA");
                            //只释放了lockA，lockB没有释放，所以导致后续线程B拿不到lockB
                            lockA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    synchronized (lockA) {
                        System.out.println("threadB get lockA");

                        System.out.println("threadB try get lockB");

                        synchronized (lockB) {
                            System.out.println("threadA get lockB");
                            System.out.println("threadA release lockB");
                            lockA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        threadB.start();
        //join：等threadA执行完了再执行main线程
        threadA.join();
        threadB.join();
        System.out.println("main thread over");
    }
}
