package common;

/**
 * Created by Song on 2017/7/11.
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println();
    }
}

public class RunnableTest {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
    }
}
