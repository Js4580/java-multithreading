package part_number_two.£part.Java_Multithreading.Problem_DeadLock;

import java.util.Random;

public class DeadLock_1 {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread thread_1 = new Thread(runner::firstThread);
        Thread thread_2 = new Thread(runner::secondThread);
        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();

        runner.finished();

    }
}

class Runner {
    private final Account account_1 = new Account();
    private final Account account_2 = new Account();

    public void firstThread() {
        Random random = new Random();
        for (int index = 0; index < 10_000; index++) {
            int amount = random.nextInt(100);
            Account.transfer(account_1, account_2, amount);
        }
    }

    public void secondThread() {
        Random random = new Random();
        for (int index = 0; index < 10_000; index++) {
            int amount = random.nextInt(100);
            Account.transfer(account_2, account_1, amount);
        }
    }

    public void finished() {
        System.out.println(account_1.getBalance() + "\n" + account_2.getBalance() + "\n"
                + "Total balance: " + (account_1.getBalance() + account_2.getBalance()));
    }
}

class Account {
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

    public static void transfer(Account account_1, Account account_2, int amount) {
        account_1.withdraw(amount);
        account_2.deposit(amount);
    }
}
