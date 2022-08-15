package part_number_two.£part.Java_Multithreading.Synchronized_keyword.part_two;

import java.util.*;

public class New {
    public static void main(String[] args) {
        new Worker().main();
    }
}

class Worker {
    Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void addToList1() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    public void addToList2() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

    public void work() {
        for (int i = 0; i < 1_000; i++) {
            addToList1();
            addToList2();
        }
    }

    public void main() {
        long before = System.currentTimeMillis();
        work();
        long after = System.currentTimeMillis();
        System.out.println("Program took " + (after - before) + "ms to run");

        System.out.println("List1 : " + list1.size());
        System.out.println("List2 : " + list2.size());
    }
}
