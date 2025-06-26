package thread;

/**
 * 线程中断
 * void interrupt()
 * boolean isInterrupted() 检测线程是否被中断，被中断则返回true。
 * boolean interrupted() 检测当前线程是否被中断，并清除中断标志。这里是获取当前调用线程的标志而不是实例对象。
 * 例如在main线程里调用了thread1.interrupted()。返回的是main线程的中断标志，等同Thread.interrupted()。
 *
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(!Thread.currentThread().isInterrupted()) {
//                    System.out.println(Thread.currentThread() + " " + Thread.currentThread().isInterrupted());
//                }
//            }
//        });
//        thread.start();
//        Thread.sleep(1000);
//        thread.interrupt();
//        thread.join();
//        System.out.println("main is over");


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {

                }
            }
        });
        thread1.start();
        thread1.interrupt();
        System.out.println("thread1.isInterrupted()=" + thread1.isInterrupted());

        //检测当前线程是否被中断，当前线程指的是main线程而不是thread1。

        System.out.println("thread1.interrupted()=" + thread1.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("Thread.interrupted()=" + Thread.interrupted());
        System.out.println("thread1.isInterrupted()=" + thread1.isInterrupted());
        thread1.join();
        System.out.println("main is over");
    }


}
