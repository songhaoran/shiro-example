package common;

/**
 * Created by Song on 2017/7/11.
 */

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
    }
}

public class ThreadTest01 {
    public static void main(String[] args) {
        new MyThread().start();
    }
}

