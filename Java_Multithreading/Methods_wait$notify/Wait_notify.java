package part_number_two.£part.Java_Multithreading.Methods_wait$notify;

import java.util.Scanner;

public class Wait_notify {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify wn = new WaitAndNotify();

        Thread thread_1 = new Thread(() -> {
            try {
                wn.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread_2 = new Thread(() -> {
            try {
                wn.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();
    }
}

class WaitAndNotify {
    final Object jn = new Object();
    public void produce() throws InterruptedException {
        synchronized (jn){
            System.out.println("Producer thread started…");
            jn.wait();
            System.out.println("Producer thread resumed…");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2_000);
        Scanner scanner = new Scanner(System.in);
        synchronized (jn){
            System.out.println("Waiting for return key pressed");
            scanner.nextLine();
            jn.notify();
            Thread.sleep(2_000);
        }
    }
}
