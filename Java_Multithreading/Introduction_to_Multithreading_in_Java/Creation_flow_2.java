
package part_number_two.£part.Java_Multithreading.Introduction_to_Multithreading_in_Java;

public class Creation_flow_2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello from MyThread" + i);
            }
        });
        thread.start();
    }
}

class Runner implements Runnable {

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from MyThread" + i);
        }
    }
}
