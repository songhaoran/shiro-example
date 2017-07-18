package common;

/**
 * Created by Song on 2017/7/11.
 */
public class ViewTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable run!");
            }
        }){
            @Override
            public void run() {
                System.out.println("subThread run!");
            }
        }.start();
    }
}
