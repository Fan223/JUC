package fan.juc.thread;

public class RunnableTask implements Runnable {

    private volatile int tickets = 20;

    @Override
    public void run() {
        while (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出一张票" + tickets--);
        }
    }

    public static void main(String[] args) {
        RunnableTask runnableTask = new RunnableTask();
        Thread thread1 = new Thread(runnableTask);
        Thread thread2 = new Thread(runnableTask);
        thread1.setName("一号窗口：");
        thread2.setName("二号窗口：");
        thread1.start();
        thread2.start();
    }
}
