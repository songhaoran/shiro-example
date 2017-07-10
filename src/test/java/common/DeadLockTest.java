package common;

/**
 * Created by Song on 2017/7/10.
 */
public class DeadLockTest {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName()+"****拿到锁1,等待锁2");
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName()+"****释放锁2,拿到锁1");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName()+"****拿到锁2,等待锁1");
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName()+"****释放锁2,拿到锁1");
                    }
                }
            }
        }).start();
    }
}
