package part_number_two.£part.Java_Multithreading.Synchronized_keyword.part_two;

public class
Update {

    private int counter;

    public static void main(String[] args) throws InterruptedException {
        part_number_two.£part.Java_Multithreading.Synchronized_keyword.Update jn = new part_number_two.£part.Java_Multithreading.Synchronized_keyword.Update();
        jn.doWork();
    }
//В данном примере использовать такой синтаксис: synchronized block = NAM synchronized - не стоит. В другом классе покажу что такой синтаксис очень ускоряет процесс
    public void increment() {
        synchronized (this) {
            counter++;
        }
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

