package review.thread_creation_management.thread_lifecycle;

public class TimeWaitingExample {
    private final Object lock = new Object();

    public void timedWaitingThread() {
        synchronized (lock) {
            try {
                System.out.println("Thread enters timed waiting for 30 seconds");
                lock.wait(30000);
                System.out.println("Thread is now runnable again after timed waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyingThread() {
        synchronized (lock) {
            System.out.println("Notifying waiting thread");
            lock.notify();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeWaitingExample example = new TimeWaitingExample();
        Thread t1 = new Thread(example::timedWaitingThread);
        t1.start();

        Thread.sleep(5000);

        Thread t2 = new Thread(example::notifyingThread);
        t2.start();

    }
}
