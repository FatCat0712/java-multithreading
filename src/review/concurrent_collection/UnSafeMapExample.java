package review.concurrent_collection;

import java.util.HashMap;

public class UnSafeMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        Runnable task = () -> {
            for(int i = 0; i < 5; i++) {
                map.put(i, Thread.currentThread().getName());
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        System.out.println("Final Map: " + map);

    }
}
