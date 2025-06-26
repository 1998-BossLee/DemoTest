package thread;

/**
 * notify()
 * 当前线程调用锁的notify方法，被唤醒的线程得等当前线程关于lock的同步块代码执行完了之后才能执行wait()后的代码。
 * 其实就是被唤醒后想执行wait()后的临界区代码还是得拿lock。
 */
public class NotifyTest {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread waitThread1 = new Thread(new WaitThread(lock), "waitThread1");
        Thread waitThread2 = new Thread(new WaitThread(lock), "waitThread2");
        Thread notifyThread = new Thread(new NotifyThread(lock), "notifyThread");
        waitThread1.start();
        waitThread2.start();
        Thread.sleep(1000);
        notifyThread.start();
    }
}

class WaitThread implements Runnable {
    Object lock;

    public WaitThread(Object lock) {
        this.lock = lock;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        synchronized (lock) {
            System.out.println(threadName + "开始进入同步代码块区域");
            try {
                lock.wait();
                System.out.println(threadName + "被唤醒了");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "准备离开同步代码块区域");
        }
    }
}

class NotifyThread implements Runnable {
    Object lock;

    public NotifyThread(Object lock) {
        this.lock = lock;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        synchronized (lock) {
            System.out.println(threadName + "开始进入同步代码块区域");
            lock.notify();
            //可以尝试notifyAll()
            System.out.println(threadName + "随机唤醒其中1个等待线程，但是它没办法马上从wait()后执行下去，得等我把synchronized走完才可以");
            try {
                System.out.println(threadName + "业务处理开始");
                // 暂停 10s 表示业务处理
                Thread.sleep(10000);
                System.out.println(threadName + "业务处理结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "准备离开同步代码块区域");
        }
        try {
            //等被唤醒的线程占住lock。经过测试，如果没有这部分明显的睡眠代码，lock给当前线程还是给waitThread是随机的。
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + "尝试重新获取lock，此时等待线程已经醒了，占着lock不放，我得等一会");
        synchronized (lock) {
            System.out.println(threadName + "拿到lock");
        }
        System.out.println(threadName + "退出同步代码块后续操作");
    }
}
