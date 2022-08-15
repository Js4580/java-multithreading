package part_number_two.£part.Java_Multithreading.Problem_DeadLock;

import java.util.Random;

public class DeadLock_2 {

    public static void main(String[] args) throws InterruptedException {
        Runner_UFC runner = new Runner_UFC();
        Thread thread_1 = new Thread(runner::firstThread);
        Thread thread_2 = new Thread(runner::secondThread);
        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();

        runner.finished();

    }
}

class Runner_UFC {
    private final Account_UFC account_1 = new Account_UFC();
    private final Account_UFC account_2 = new Account_UFC();

    public void firstThread() {
        Random random = new Random();
        for (int index = 0; index < 10_000; index++) {
            synchronized (account_1) {
                synchronized (account_2) {
                    int amount = random.nextInt(100);
                    Account_UFC.transfer(account_1, account_2, amount);
                }
            }
        }
    }

    public void secondThread() {
        Random random = new Random();
        for (int index = 0; index < 10_000; index++) {
            synchronized (account_1) {
                synchronized (account_2) {
                    int amount = random.nextInt(100);
                    Account_UFC.transfer(account_2, account_1, amount);
                }
            }
        }
    }

    public void finished() {
        System.out.println(account_1.getBalance() + "\n" + account_2.getBalance() + "\n"
                + "Total balance: " + (account_1.getBalance() + account_2.getBalance()));
    }
}

class Account_UFC {
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

    public static void transfer(Account_UFC account_1, Account_UFC account_2, int amount) {
        account_1.withdraw(amount);
        account_2.deposit(amount);
    }
}