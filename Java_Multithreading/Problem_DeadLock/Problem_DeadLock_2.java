package part_number_two.£part.Java_Multithreading.Problem_DeadLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Problem_DeadLock_2 {

    public static void main(String[] args) throws InterruptedException {
        Runner_WWA runner = new Runner_WWA();
        Thread thread_1 = new Thread(runner::firstThread);
        Thread thread_2 = new Thread(runner::secondThread);
        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();

        runner.finished();

    }
}

class Runner_WWA {
    private final Account_WWA account_1 = new Account_WWA();
    private final Account_WWA account_2 = new Account_WWA();

    private final Lock lock_1 = new ReentrantLock();
    private final Lock lock_2 = new ReentrantLock();

    public void takeLocks(Lock lock_1, Lock lock_2) {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;

        while (true) {
            try {
                firstLockTaken = lock_1.tryLock();
                secondLockTaken = lock_2.tryLock();
            } finally {
                if (firstLockTaken && secondLockTaken)
                    return;
                if (firstLockTaken)
                    lock_1.unlock();
                if (secondLockTaken)
                    lock_2.unlock();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void firstThread() {
        Random random = new Random();
        for (int index = 0; index < 10_000; index++) {
            takeLocks(lock_1, lock_2);
            int amount = random.nextInt(100);
            try {
                Account_WWA.transfer(account_1, account_2, amount);
            } finally {
                lock_1.unlock();
                lock_2.unlock();
            }
        }
    }

    public void secondThread() {
        Random random = new Random();
        for (int index = 0; index < 10_000; index++) {
            takeLocks(lock_2, lock_1);
            int amount = random.nextInt(100);
            try {
                Account_WWA.transfer(account_2, account_1, amount);
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

class Account_WWA {
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

    public static void transfer(Account_WWA account_1, Account_WWA account_2, int amount) {
        account_1.withdraw(amount);
        account_2.deposit(amount);
    }
}
