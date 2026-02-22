package review.synchronisation_techniques.lock_in_java;

import java.util.concurrent.locks.ReentrantLock;

public class CounterWithTryLock {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        if(lock.tryLock()) {
//            Attempt to acquire the lock without blocking
            try {
                count++;
            }finally {
                lock.unlock();
            }
        }
        else {
            System.out.println("Could not acquire lock, skipping increment");
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        CounterWithTryLock counter = new CounterWithTryLock();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            //
        }

        System.out.println("Final count with try lock: " + counter.getCount());

    }
}
