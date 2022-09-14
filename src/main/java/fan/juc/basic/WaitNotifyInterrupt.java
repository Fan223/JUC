package fan.juc.basic;

public class WaitNotifyInterrupt {

    static Object resource = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("---begin---");
                // 阻塞当前线程
                synchronized (resource) {
                    resource.wait();
                }
                System.out.println("---end---");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();

        Thread.sleep(1000);
        System.out.println("---begin interrupt thread");
        thread.interrupt();
        System.out.println("---endk interrupt thread");
    }
}
