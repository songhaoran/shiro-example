package common;

/**
 * Created by Song on 2017/7/10.
 */
public class Ticket_1Test implements Runnable {
    private static Integer num = 100;

    Object obj = new Object();

    @Override
    public void run() {

        if ("Thread-0".equals(Thread.currentThread().getName())) {
            while (true) {
                synchronized (this.getClass()) {
                    if (num > 0) {
                        try {
                            Thread.sleep(3);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ":" + num--);
                    }
                }
            }
        } else {
            while (true) {
                sale();
            }
        }
    }

    private synchronized static void sale() {
        if (num > 0) {
            try {
                Thread.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + num--);
        }
    }
}
