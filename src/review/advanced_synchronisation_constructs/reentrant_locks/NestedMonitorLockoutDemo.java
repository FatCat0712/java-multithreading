package review.advanced_synchronisation_constructs.reentrant_locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class LockoutExample {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean ready = false;

    public void waitForCondition() {
        lock.lock();
        try {
            while (!ready) {
                System.out.println(Thread.currentThread().getName() + " waiting for condition");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " proceeding after condition met");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void signalCondition() {
        lock.lock();
        try {
            ready = true;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + " signaled condition");
        } finally {
            lock.unlock();
        }
    }
}


public class NestedMonitorLockoutDemo {
    public static void main(String[] args) throws InterruptedException {
        LockoutExample example = new LockoutExample();
        Thread waitingThread = new Thread(example::waitForCondition);
        Thread signalingThread = new Thread(example::signalCondition);

        waitingThread.start();

        Thread.sleep(100);

        signalingThread.start();

    }
}
