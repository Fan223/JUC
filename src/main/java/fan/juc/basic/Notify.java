package fan.juc.basic;

public class Notify {

    private static volatile Object resource = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取 resource 共享资源的监视器锁
                synchronized (resource) {
                    System.out.println("threadA get resource lock");
                    try {
                        System.out.println("threadA begin wait");
                        resource.wait();
                        System.out.println("threadA end wait");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取 resource 共享资源的监视器锁
                synchronized (resource) {
                    System.out.println("threadB get resource lock");
                    try {
                        System.out.println("threadB begin wait");
                        resource.wait();
                        System.out.println("threadB end wait");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource) {
                    System.out.println("ThreadC begin notify");
                    resource.notifyAll();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");
    }
}
