package common;

/**
 * Created by Song on 2017/7/10.
 */
class DeadLock implements Runnable{
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    @Override
    public void run() {
        if ("Thread-0".equals(Thread.currentThread().getName())) {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "****拿到锁1,等待锁2");
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "****释放锁2,拿到锁1");
                }
            }
        } else {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "****拿到锁2,等待锁1");
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "****释放锁1,拿到锁2");
                }
            }
        }
    }
}

public class DeadLockTest_1{
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock).start();
        new Thread(deadLock).start();
    }
}

