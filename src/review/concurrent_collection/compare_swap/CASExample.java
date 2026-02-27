package review.concurrent_collection.compare_swap;

import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int current;
        int newValue;

        do {
            current = counter.get();
            newValue = current + 1;
        } while (!counter.compareAndSet(current, newValue));
    }

    public int getValue() {
        return counter.get();
    }
}


public class CASExample {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final value: " + counter.getValue());
    }
}
