package part_number_two.£part.Java_Multithreading.Synchronized_keyword;

public class Synchronized {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Synchronized jn = new Synchronized();
        jn.doWork();
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10_000; index++) {
                    counter++;
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10_000; index++) {
                    counter++;
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
