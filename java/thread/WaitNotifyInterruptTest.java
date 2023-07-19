package thread;

public class WaitNotifyInterruptTest {

    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException{
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("threadA begin");
                    synchronized (obj) {
                        obj.wait();
                    }
                    System.out.println("threadA end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        System.out.println("begin interrupt threadA ");
        //threadA通过wait释放了锁obj，但是进入阻塞状态，此时被main线程打断
        threadA.interrupt();
        System.out.println("end interrupt theadA");
    }

}
