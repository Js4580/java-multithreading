package part_number_two.£part.Java_Multithreading.About_throws_exception_InterruptedException;

import java.util.Random;

public class Throws {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            Random random = new Random();
            for (int index = 0; index < 1_000_000_000; index++) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread was interrupted");
                    break;
                }
                Math.sin(random.nextInt());
            }
        });
        System.out.println("Starting thread");
        thread.start();

        Thread.sleep(1_000);
        thread.interrupt();

        thread.join();

//        thread.stop();
        System.out.println("Thread has finished");
    }
}
