package part_number_two.£part.Java_Multithreading.Class_CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CDL {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int idx = 0; idx < 3; idx++)
            executorService.submit(new Processor(countDownLatch));
        executorService.shutdown();
        countDownLatch.await();
        System.out.println("Latch has been opened, main thread proceeding!");
    }
}

class Processor implements Runnable {
    private final CountDownLatch countDownLatch;

    public Processor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}