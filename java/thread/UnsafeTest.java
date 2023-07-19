package thread;

import sun.misc.Unsafe;

public class UnsafeTest {

    static final Unsafe unsafe = Unsafe.getUnsafe();

    static long stateOffset = 0;

    private volatile long state = 0;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnsafeTest test = new UnsafeTest();
        boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(success);
    }


}
