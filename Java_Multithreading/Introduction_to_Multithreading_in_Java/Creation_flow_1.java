package part_number_two.£part.Java_Multithreading.Introduction_to_Multithreading_in_Java;

public class Creation_flow_1 {
    public static void main(String[] args) throws InterruptedException {
        JN jn = new JN();
        jn.start();
        /*Thread.sleep(1000);*/
        JN jn4580 = new JN();
        jn4580.start();
    }
}
class JN extends Thread{
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
