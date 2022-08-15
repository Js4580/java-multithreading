package part_number_two.£part.Java_Multithreading.Class_Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Own_Semaphore_2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        Connection_MMA connection = Connection_MMA.getConnection();
        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> {
                try {
                    connection.work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class Connection_MMA {
    private final static Connection_MMA CONNECTION = new Connection_MMA();
    private int connectionCount;
    private final Semaphore SEMAPHORE = new Semaphore(10);

    private Connection_MMA() {

    }

    public static Connection_MMA getConnection() {
        return CONNECTION;
    }

    public void work() throws InterruptedException {
        SEMAPHORE.acquire();
        try {
            doWork();
        } finally {
            SEMAPHORE.release();
        }
    }

    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionCount++;
            System.out.println(connectionCount);
        }

        Thread.sleep(5_000);
        synchronized (this) {
            connectionCount--;
        }

    }
}

