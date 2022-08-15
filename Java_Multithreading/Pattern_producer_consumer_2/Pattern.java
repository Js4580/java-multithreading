package part_number_two.£part.Java_Multithreading.Pattern_producer_consumer_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Pattern {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread thread_1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread_2 = new Thread(() -> {
            try {
                pc.consume();
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

class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT)
                    lock.wait();
                queue.offer(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            Thread.sleep(1_000);
            synchronized (lock) {
                while (queue.size() == 0)
                    lock.wait();
                int value = queue.poll();
                System.out.println(value);
                System.out.println("Queue size is " + queue.size());
                lock.notify();
            }
        }
    }
}