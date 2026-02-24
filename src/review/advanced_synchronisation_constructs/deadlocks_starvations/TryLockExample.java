package review.advanced_synchronisation_constructs.deadlocks_starvations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    private final ReentrantLock lock = new ReentrantLock();

    public boolean tryLock(int timeout, TimeUnit unit) throws InterruptedException {
        return lock.tryLock(timeout, unit);
    }

    public void unlock() {
        lock.unlock();
    }

    public void doSomething() {
        System.out.println(Thread.currentThread().getName() + " is doing something with the resource.");
    }
}

public class TryLockExample {
    public static void main(String[] args) {
        review.advanced_synchronisation_constructs.deadlocks_starvations.deadlock_detection.Resource resource1 = new review.advanced_synchronisation_constructs.deadlocks_starvations.deadlock_detection.Resource();
        review.advanced_synchronisation_constructs.deadlocks_starvations.deadlock_detection.Resource resource2 = new review.advanced_synchronisation_constructs.deadlocks_starvations.deadlock_detection.Resource();

        Thread thread1 = new Thread(() -> accessResource(resource1, resource2));
        Thread thread2 = new Thread(() -> accessResource(resource1, resource2));

        thread1.start();
        thread2.start();

    }

    private static void accessResource(review.advanced_synchronisation_constructs.deadlocks_starvations.deadlock_detection.Resource resource1, review.advanced_synchronisation_constructs.deadlocks_starvations.deadlock_detection.Resource resource2) {
        while (true) {
            try {
                if (resource1.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock on resource1");
                    try {
                        if (resource2.tryLock(1, TimeUnit.SECONDS)) {
                            System.out.println(Thread.currentThread().getName() + " acquired lock on resource2");
                            resource1.doSomething();
                            resource2.doSomething();
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName() + " could not acquire lock on resource2. Releasing lock on resource1 and retrying");
                        }
                    } finally {
                        resource1.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not acquire lock on resource1. Releasing lock on resource1 and retrying");
                }

                Thread.sleep(100);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted");
            } finally {
                resource2.unlock();
            }

        }
    }
}
