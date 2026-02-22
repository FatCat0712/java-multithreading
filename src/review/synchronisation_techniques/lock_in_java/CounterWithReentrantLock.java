package review.synchronisation_techniques.lock_in_java;

import java.util.concurrent.locks.ReentrantLock;

public class CounterWithReentrantLock {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        CounterWithReentrantLock counter = new CounterWithReentrantLock();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
           for(int i = 0; i < 1000; i++) {
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

//        Expected count should be 2000 since both threads update the same counter
        System.out.println("Final count with ReentrantLock: " + counter.getCount());
    }
}
