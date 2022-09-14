package fan.juc.basic;

public class JoinInterrupt {
    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            System.out.println("threadOne begin run");
            for (;;) {

            }
        });
        // 获取主线程
        final Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 中断主线程
            mainThread.interrupt();
        });
        // 启动子线程
        threadOne.start();
        threadTwo.start();
        // 等待线程 one 执行结束
        try {
            threadOne.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
