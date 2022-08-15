package part_number_two.£part.Java_Multithreading.Callable_Future;

import java.util.Random;
import java.util.concurrent.*;

public class CF_2 {
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Starting");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished");
            Random random = new Random();
//            можем вот так
            int randomValue = random.nextInt();
            if (randomValue < 5)
                throw new Exception("You stupid");
            return random.nextInt(10);
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        int result = 0;
        try {
            result = future.get();
        } catch (ExecutionException e) {
            e.getMessage();
        }

        System.out.println(result);
    }
}
