package part_number_two.£part.Java_Multithreading.Problem_DeadLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock_3 {

    public static void main(String[] args) throws InterruptedException {
        Runner_MMA runner = new Runner_MMA();
        Thread thread_1 = new Thread(runner::firstThread);
        Thread thread_2 = new Thread(runner::secondThread);
        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();

        runner.finished();

    }
}

class Runner_MMA {
    private final Account_MMA account_1 = new Account_MMA();
    private final Account_MMA account_2 = new Account_MMA();

    private final Lock lock_1 = new ReentrantLock();
    private final Lock lock_2 = new ReentrantLock();

    public void firstThread() {
        Random random = new Random();
        for (int index = 0; index < 10_000; index++) {
            lock_1.lock();
            lock_2.lock();
            int amount = random.nextInt(100);
            try {
                Account_MMA.transfer(account_1, account_2, amount);
            } finally {
                lock_1.unlock();
                lock_2.unlock();
            }
        }
    }

    public void secondThread() {
        Random random = new Random();
        for (int index = 0; index < 10_000; index++) {
            lock_1.lock();
            lock_2.lock();
            int amount = random.nextInt(100);
            try {
                Account_MMA.transfer(account_2, account_1, amount);
            } finally {
                lock_1.unlock();
                lock_2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println(account_1.getBalance() + "\n" + account_2.getBalance() + "\n"
                + "Total balance: " + (account_1.getBalance() + account_2.getBalance()));
    }
}

class Account_MMA {
    private int balance = 10_000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account_MMA account_1, Account_MMA account_2, int amount) {
        account_1.withdraw(amount);
        account_2.deposit(amount);
    }
}
