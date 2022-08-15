package part_number_two.£part.Java_Multithreading.Class_ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RL {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread_1 = new Thread(task::firstThread);
        Thread thread_2 = new Thread(task::secondThread);

        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();

        task.showCounter();
    }
}

class Task {
    private int counter;
    private final Lock lock_1 = new ReentrantLock();

    private void increment() {
        for (int index = 0; index < 10_000; index++)
            counter++;

    }

    public void firstThread() {
        lock_1.lock();
        increment();
        lock_1.unlock();
    }

    public void secondThread() {
        lock_1.lock();
        increment();
        lock_1.unlock();
    }
    public void showCounter(){
        System.out.println("Counter is " + counter);
    }
}
