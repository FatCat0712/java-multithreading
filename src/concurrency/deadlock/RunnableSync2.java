package concurrency.deadlock;

public class RunnableSync2 implements Runnable{
    private Object lock1 = null;
    private Object lock2 = null;

    public RunnableSync2(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " attempting to Lock lock 2");
        synchronized (lock2) {
            System.out.println(threadName + " locked Lock 2");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //ignore
            }

            System.out.println(threadName + " attempting to Lock lock 2");
            synchronized (lock1) {
                System.out.println(threadName + " locked Lock 1");
//                do the work - now that both locks have been acquired
            }
            System.out.println(threadName + "unlocking lock 1");

        }

    }
}
