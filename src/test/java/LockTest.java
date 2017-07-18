import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Song on 2017/7/11.
 */
class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition putCondition = lock.newCondition();
    final Condition takeCondition = lock.newCondition();

    final Object[] items = new Object[100];
    int putIndex, takeIndex, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length || items[putIndex] != null) {
                putCondition.await();
            }
            items[putIndex] = x;
            System.out.println("生产了" + putIndex);
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            ++count;
            takeCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0 || items[takeIndex] == null) {
                takeCondition.await();
            }
            Object x = items[takeIndex];
            items[takeIndex] = null;
            System.out.println("*****消费了" + takeIndex);
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            --count;
            putCondition.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}

class Pro implements Runnable {
    private BoundedBuffer boundedBuffer;

    public Pro(BoundedBuffer boundedBuffer) {
        this.boundedBuffer = boundedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                boundedBuffer.put(new Object());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Customer implements Runnable {
    private BoundedBuffer boundedBuffer;

    public Customer(BoundedBuffer boundedBuffer) {
        this.boundedBuffer = boundedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                boundedBuffer.take();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        Pro pro = new Pro(boundedBuffer);
        Customer con = new Customer(boundedBuffer);

        new Thread(pro).start();
        new Thread(pro).start();
        new Thread(con).start();
        new Thread(con).start();
    }
}
