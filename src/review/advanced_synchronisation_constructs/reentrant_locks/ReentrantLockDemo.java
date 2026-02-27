package review.advanced_synchronisation_constructs.reentrant_locks;

import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock in outerMethod");
            innerMethod();
        }finally {
            lock.unlock();
        }

    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock in innerMethod");
        } finally {
            lock.unlock();
        }
    }
}


public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        Thread thread = new Thread(example::outerMethod);
        thread.start();


    }
}
