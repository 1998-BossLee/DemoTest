package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 *
 */
public class ParkTest {

    public void testPark() {
        LockSupport.park(this);
    }

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child park  interrupted="+Thread.currentThread().isInterrupted());
//                while (!Thread.currentThread().isInterrupted()) {
//
//                }
                LockSupport.park(this);
                System.out.println("child park interrupted="+Thread.currentThread().isInterrupted());
            }

        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main begin unpark");
    }

}
