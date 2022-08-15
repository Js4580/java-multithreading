package part_number_two.£part.Java_Multithreading.Callable_Future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CF_1 {
    private static int result;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            System.out.println("Starting");
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished");
            result++;
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(result);
    }

    public static int calculate() {
        return 5 + 4;
    }
}
