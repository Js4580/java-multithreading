package part_number_two.£part.Java_Multithreading.Synchronized_keyword;

public class Update {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Update jn = new Update();
        jn.doWork();
    }

    public synchronized void increment() {
        counter++;
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10_000; index++) {
                    increment();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10_000; index++) {
                    increment();
                }
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(counter);
    }
}
