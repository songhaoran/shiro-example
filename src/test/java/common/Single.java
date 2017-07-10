package common;

/**
 * Created by Song on 2017/7/10.
 * 单例:饿汉模式下的多线程安全
 */
public class Single {
    private static Single single = null;

    private Single() {

    }

    /**
     * 会有线程安全问题,可能会产生多个线程获取的多个单例对象的情形
     */
    public static Single getInstance(){
        if (single == null) {
            single = new Single();
        }
        return single;
    }

    /**
     * 没有线程安全问题,但效率低
     */
    public static synchronized Single getInstance_1(){
        if (single == null) {
            single = new Single();
        }
        return single;
    }

    /**
     * 没有线程安全问题,解决了效率低的问题
     */
    public static Single getInstance_2(){
        if (single == null) {
            synchronized (Single.class) {
                if (single == null) {
                    single = new Single();
                }
            }
        }
        return single;
    }

}
