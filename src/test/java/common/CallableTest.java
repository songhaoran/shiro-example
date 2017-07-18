package common;

import java.util.concurrent.*;

/**
 * Created by Song on 2017/7/11.
 */

public class CallableTest {
    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<Object> future = threadPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println();
                return "result";
            }
        });
        Object o = future.get();
    }
}
