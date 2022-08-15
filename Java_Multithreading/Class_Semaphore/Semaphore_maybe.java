package part_number_two.£part.Java_Multithreading.Class_Semaphore;

import java.util.concurrent.Semaphore;

public class Semaphore_maybe {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();

        System.out.println("All permits hava been acquired!");

        semaphore.release();
        semaphore.acquire();
        System.out.println("Can't reach here...");
        System.out.println(semaphore.availablePermits());
    }
}
