package part_number_two.£part.Java_Multithreading.Class_CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CDL_conversely {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int idx = 0; idx < 3; idx++)
            executorService.submit(new Processor_MMA(idx, countDownLatch));
        executorService.shutdown();
        for (int idx = 0; idx < 3; idx++) {
            Thread.sleep(1_000);
            countDownLatch.countDown();
        }
    }
}

class Processor_MMA implements Runnable {
    private final int id;
    private final CountDownLatch countDownLatch;

    public Processor_MMA(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with id " + id + " proceeded.");
    }
}
