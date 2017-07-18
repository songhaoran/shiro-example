package common;

/**
 * Created by Song on 2017/7/10.
 */
public class ResourceTest {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(new Input(resource)).start();
        new Thread(new OutPut(resource)).start();
    }
}

/**
 * 向资源写东西
 */
class Input implements Runnable {
    private Resource resource;

    public Input() {
    }

    public Input(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            synchronized (resource) {
                if (!resource.getFlag()) {
                    if (x == 0) {
                        resource.setName("Jack");
                        //sleep()时,不释放锁,因此在这里使用sleep是没有任何作用的
                        /*try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                        }*/
                        resource.setSex("male");
                        x = 1;
                    } else {
                        resource.setName("露丝");

                        resource.setSex("女");
                        x = 0;
                    }
                    resource.setFlag(true);
                    resource.notify();
                } else {
                    try {
                        resource.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

/**
 * 从资源取东西
 */
class OutPut implements Runnable {
    private Resource resource;


    public OutPut() {
    }

    public OutPut(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (resource) {
                if (resource.getFlag()) {
                    System.out.println("name:" + resource.getName() + "***sex:" + resource.getSex());
                    resource.setFlag(false);
                    resource.notify();
                } else {
                    try {
                        resource.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


class Resource {
    private String name;
    private String sex;

    private Boolean flag = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
