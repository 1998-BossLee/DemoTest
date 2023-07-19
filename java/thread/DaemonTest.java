package thread;

/**
 * 守护线程
 * 线程分用户线程和守护线程，例如垃圾回收线程是守护线程，main线程是用户线程。当没有用户线程执行的时候，JVM正常退出，守护线程随之消亡。
 * 设置守护线程的方式：setDaemon()。必须在start()方法之前调用。
 */
public class DaemonTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {

                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("main is over");
    }

}
