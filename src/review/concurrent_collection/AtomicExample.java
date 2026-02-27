package review.concurrent_collection;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

}


public class AtomicExample {
    public static void main(String[] args) {
            AtomicCounter counter = new AtomicCounter();

            Runnable task = () -> {
                for(int i = 0; i < 1_000_000; i++) {
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

        System.out.println("Final Count: " + counter.getCount());

    }
}
