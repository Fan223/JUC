package fan.juc.basic;

public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Child threadOne over");
        });

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Child threadTwo over");
        });

        // 启动子线程
        threadOne.start();
        threadTwo.start();
        System.out.println("Wait All Child Thread Over");

        // 等待子线程执行完毕，返回
        threadOne.join();
        threadTwo.join();
        System.out.println("All Child Thread Over");
    }
}
