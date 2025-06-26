package thread;

import java.util.concurrent.Callable;

public class Test {

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        Object lock = new Object();
        synchronized (lock) {

        }

    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("ThreadName=" + this.getName() + " has run");
    }
}

class MyCaller implements Callable<String> {
    @Override
    public String call(){
        System.out.println("通过实现Callable接口创建线程，可以返回参数");
        return "String参数";
    }
}
