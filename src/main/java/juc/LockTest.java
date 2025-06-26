package juc;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * ReentrantLock 可重入锁
 * <p>
 * <p>
 * ReentrantReadWriteLock 可重入读写锁
 * 凡是带Interruptibly的方法，都是会对中断进行响应，当其他线程中断当前线程，当前线程会抛中断异常。
 * void lock() 获取失败会阻塞挂起
 * boolean tryLock() 获取失败不会被阻塞
 * boolean tryLock(long timeout, TimeUnit unit)
 */
public class LockTest {

    public static void main(String[] args) throws Exception{
        StampedLock lock = new StampedLock();


    }

}//131072 196608
