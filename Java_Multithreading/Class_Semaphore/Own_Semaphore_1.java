package part_number_two.£part.Java_Multithreading.Class_Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Own_Semaphore_1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        Connection connection = Connection.getConnection();
        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> {
                try {
                    connection.doWork();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class Connection {
    private final static Connection CONNECTION = new Connection();
    private int connectionCount;

    private Connection() {

    }

    public static Connection getConnection() {
        return CONNECTION;
    }

    public void doWork() throws InterruptedException {
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
