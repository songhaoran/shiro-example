package common;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Song on 2017/7/11.
 * 流程:当资源中flag为false时,生产者向资源中设置姓名和性别,设置完成后将flag置为true;
 * 当资源中flag为true时,消费者从资源中获取姓名和性别,获取完成后将flag置为false
 */
public class LockTest {
    public static void main(String[] args) {
        Resource_1 resource = new Resource_1();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}

class Resource_2 {
    private String name;

    private int count = 0;

    boolean flag = false;

    Lock aa = new ReentrantLock();
    Condition producer_condition = aa.newCondition();
    Condition consumer_condition = aa.newCondition();

    public void set(String name) {
        try {
            while (flag) {
                producer_condition.await();
            }
            this.name = name;
            this.count++;
            flag = true;
            System.out.println("设置" + name + "***************-" + count);
            consumer_condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get() {
        try {
            while (!flag) {
                consumer_condition.wait();
            }
            System.out.println("获取" + name + "****-" + count);
            flag = false;
            producer_condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Producer_1 implements Runnable {
    private Resource_2 resource;

    public Producer_1() {
    }

    public Producer_1(Resource_2 resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.set("烤鸭");
        }
    }
}

class Consumer_1 implements Runnable {
    private Resource_2 resource;

    public Consumer_1() {
    }

    public Consumer_1(Resource_2 resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.get();
        }
    }
}

