package common;

/**
 * Created by Song on 2017/7/11.
 */
class NewThread implements Runnable {
    boolean flag = true;
    @Override
    public synchronized void run() {
        while (flag) {
            try {
                this.wait();
            } catch (Exception e) {
                System.out.println("在等待");
            }
            System.out.println("aa");
        }
    }
}
public class StopThread {
    public static void main(String[] args) {
        NewThread newThread = new NewThread();
        Thread thread = new Thread(newThread);thread.start();

        thread.interrupt();
        newThread.flag = false;
    }
}
