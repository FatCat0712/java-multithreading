package review.advanced_synchronisation_constructs.deadlocks_starvations.deadlock_detection;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    private final Lock lock = new ReentrantLock();

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}


public class DeadlockDetectionExample {
    public static void main(String[] args) {
            Resource resource1 = new Resource();
            Resource resource2 = new Resource();

//            Creating two threads that will enter a deadlock
        Thread thread1 = new Thread(() -> {
            try {
                resource1.lock();
                System.out.println(Thread.currentThread().getName() + " acquired lock on resource 1");

//                Adding a small delay to increase chances of deadlock
                Thread.sleep(50);

                resource2.lock();
                System.out.println(Thread.currentThread().getName() + " acquired lock on resource 2");
            }catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }finally {
                resource1.unlock();
                resource2.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                resource2.lock();
                System.out.println(Thread.currentThread().getName() + " acquired lock on resource 1");

//                Adding a small delay to increase chances of deadlock
                Thread.sleep(50);

                resource1.lock();
                System.out.println(Thread.currentThread().getName() + " acquired lock on resource 2");
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }finally {
                resource2.unlock();
                resource1.unlock();
            }
        });

        thread1.start();
        thread2.start();

//        Starting a separate thread to monitor for deadlocks
        Thread deadlockDetector = new Thread(() -> {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

            while(true) {
//                Check for deadlocked threads
                long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
                if(deadlockedThreads != null) {
                    System.out.println("Deadlock detected!");

//                    Print information about deadlocked threads
                    for (long threadId: deadlockedThreads) {
                        System.out.println("Thread ID: " + threadId + " - " + threadMXBean.getThreadInfo(threadId));
                    }

//                    Normally, you might take further action here, like logging or alerting, but for this example, We'll
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        deadlockDetector.setDaemon(true);
        deadlockDetector.start();
    }
}
