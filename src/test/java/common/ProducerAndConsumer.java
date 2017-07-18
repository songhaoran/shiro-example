package common;

/**
 * Created by Song on 2017/7/11.
 * 流程:当资源中flag为false时,生产者生产烤鸭,生产完成后将flag置为true;
 * 当资源中flag为true时,消费者获取烤鸭,获取完成后将flag置为false
 */
public class ProducerAndConsumer {
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

class Resource_1 {

    private int count=0;

    boolean flag = false;

    public synchronized void set() {
        try {
            while (flag) {
                this.wait();
            }
            this.count++;
            flag = true;
            System.out.println("生产烤鸭,编号***************-" + count);
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void get() {
        try {
            while (!flag) {
                this.wait();
            }
            System.out.println("获取烤鸭,编号****-" + count);
            flag = false;
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {
    private Resource_1 resource;

    public Producer(Resource_1 resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.set();
        }
    }
}

class Consumer implements Runnable {
    private Resource_1 resource;

    public Consumer(Resource_1 resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.get();
        }
    }
}
