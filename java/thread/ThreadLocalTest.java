package thread;

import sun.misc.Unsafe;

import java.util.Arrays;

/**
 * ThreadLocal 当作一个map来使用，key是各个线程，value是各个线程存储的对象值，只能存一个，想存多个则存Map。
 * 存储的这个值放在Thread.threadLocals里，ThreadLocal只是个工具来调用而已。
 * 不支持继承性，不支持获取父线程的值。
 * <p>
 * InheritableThreadLocal
 * 子线程获取父线程的变量，这个动作发送在线程创建的时候，如果父线程的InheritableThreadLocal有值，则复制一份给子线程。
 * 后续父线程改动与子线程无关。
 */
public class ThreadLocalTest {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    static void print(String str) {
        System.out.println(str + ":" + threadLocal.get());
        //threadLocal.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        inheritableThreadLocal.set("main");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("key");
                print("thread1");
                System.out.println("thread1 remove after " + ":" + threadLocal.get());
                System.out.println("thread1 inheritableThreadLocal.get()=" + inheritableThreadLocal.get());
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(Thread.currentThread().getName());
                //threadLocal.remove();
                print("thread2");
                System.out.println("thread2 remove after " + ":" + threadLocal.get());
            }
        });
        inheritableThreadLocal.set("main2");
        Thread.sleep(1000);
        thread1.start();
        thread2.start();
        System.out.println("main inheritableThreadLocal.get()" + inheritableThreadLocal.get());

    }



}

