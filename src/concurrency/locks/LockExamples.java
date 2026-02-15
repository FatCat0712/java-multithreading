package concurrency.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExamples {
    /*
    *  1. Synchronized blocks must be contained within a single method.
    * lock.lock() and lock.unlock() can be called from different methods
    *
    * 2. lock.lock() and lock.unlock() provides the same visibility and happens
    * before guarantees as entering and exiting a synchronized block
    *
    * 3. Synchronized blocks are always reentrant. Lock could decide not
    * to be
    *
    * 4. Synchronized blocks do not guarantee fairness. Locks can
    *
    * */



    public static void main(String[] args) {
//        lockBasics();
//        lockInterruptibly();
//        tryLock();

        ReentrantLock lock = new ReentrantLock();
        int holdCount = lock.getHoldCount();
        int queueLength = lock.getQueueLength();
        boolean hasQueueThisThread = lock.hasQueuedThread(Thread.currentThread());
        boolean hasQueuedThreads = lock.hasQueuedThreads();
        boolean isFair = lock.isFair();
        boolean isLocked = lock.isLocked();
        boolean isHeldByCurrentThread = lock.isHeldByCurrentThread();
    }

    private static void tryLock() {
        Lock lock = new ReentrantLock();
        try {
            boolean lockSuccessful = lock.tryLock();
            System.out.println("Lock successful: " + lockSuccessful);
        }finally {
            lock.unlock();
        }
    }

    private static void tryLockWithFairness() {
        Lock lock = new ReentrantLock();
        try {
            boolean lockSuccessful = lock.tryLock(1000, TimeUnit.MILLISECONDS);
            System.out.println("Lock successful: " + lockSuccessful);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    private static void lockBasics() {
        Lock lock = new ReentrantLock(true);
        Runnable runnable = () -> { lockSleepUnlock(lock, 1000);};

        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");
        Thread thread3 = new Thread(runnable, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

    }

    private static void lockInterruptibly() {
        Lock lock = new ReentrantLock();
        Thread.currentThread().interrupt();
        try {
            lock.lockInterruptibly();
            lock.unlock();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

    }

    private static void lockSleepUnlock(Lock lock, long timeMillis) {
        try {
            lock.lock();
            printThreadMsg(" holds the lock.");
            sleep(timeMillis);
        }finally {
            lock.unlock();
        }
    }

    private static void printThreadMsg(String message) {
        System.out.println(Thread.currentThread().getName() + message);
    }

    private static void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}
