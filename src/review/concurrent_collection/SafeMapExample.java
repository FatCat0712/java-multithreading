package review.concurrent_collection;

import java.util.concurrent.ConcurrentHashMap;

public class SafeMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        Runnable task = () -> {
            for(int i = 0; i < 5; i++) {
                map.put(i, Thread.currentThread().getName());
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        }

        System.out.println("Final map: " + map);


    }
}
