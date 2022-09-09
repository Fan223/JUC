package fan.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallerTask implements Callable<String> {

    private volatile int tickets = 20;

    @Override
    public String call() throws Exception {
        while (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出一张票" + tickets--);
        }

        return "已售完";
    }

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        Thread thread1 = new Thread(futureTask);
        Thread thread2 = new Thread(futureTask);
        thread1.setName("一号窗口：");
        thread2.setName("二号窗口：");
        thread1.start();
        thread2.start();

        try {
            String result = futureTask.get();
            System.out.println("result：" + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
