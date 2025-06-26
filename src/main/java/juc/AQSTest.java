package juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS 抽象方法
 * state 状态信息，通过getState()、setState()、compareAndSetState()方法修改其值，不同的实现类里意义不同
 * ReentrantLock可重入锁。表示当前线程获取锁的可重入次数
 * ReentrantReadWriteLock读写锁。高16位表示获取读锁次数，低16位表示获取写锁次数。
 * 对于semaphore来说，state表示可用信号个数。
 * 对于CountDownLatch来说，state表示计数器当前的值。
 *
 *
 * await()
 *
 */
public class AQSTest {

    public static void main(String[] args) {

    }


    static final class Node {
        //标记该线程是获取共享资源时被阻塞挂起后放入QAS队列的
        static final Node SHARED = new Node();
        //标记该线程是获取独占资源时被阻塞挂起后放入QAS队列的
        static final Node EXCLUSIVE = null;
        //当前线程的等待状态：cancelled被取消了、signal需要被唤醒、condition在条件队列里等待、propagate释放共享资源时需要通知其他节点
        volatile int waitStatus;
        volatile Node prev;
        volatile Node next;
        volatile Thread thread;
        Node nextWaiter;
    }

}


