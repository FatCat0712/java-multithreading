package review.concurrent_collection.compare_swap;

import java.util.concurrent.atomic.AtomicBoolean;

class SpinLock{
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while(!lock.compareAndSet(false, true)) {
//           loop until the lock is acquired
        }
    }

    public void unlock() {
        lock.set(false);
    }
}


public class SpinLockExample {
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        Runnable task = () -> {
          spinLock.lock();
          try {
              System.out.println(Thread.currentThread().getName() + " acquired the lock");
          }finally {
              spinLock.unlock();
              System.out.println(Thread.currentThread().getName() + " released the lock");
          }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();




    }
}
