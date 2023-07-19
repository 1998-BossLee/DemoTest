package juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class QueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(11);
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        DelayQueue delayQueue = new DelayQueue();
    }

}
