package review.thread_creation_management.thread_lifecycle;

public class WaitingExample {
    private final Object lock = new Object();

    public void waitingThread() {
        synchronized (lock) {
            try {
                System.out.println("Thread is in waiting state...");
                lock.wait();
                System.out.println("Thread is now runnable after being notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyingThread() {
        synchronized (lock) {
            System.out.println("Notifying waiting thread...");
            lock.notify();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitingExample example = new WaitingExample();
        Thread t1 = new Thread(example::waitingThread);
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(example::notifyingThread);
        t2.start();


    }
}
