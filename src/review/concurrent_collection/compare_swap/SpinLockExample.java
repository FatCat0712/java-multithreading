package review.concurrent_collection.compare_swap;

import java.util.concurrent.atomic.AtomicBoolean;

class SpinLock{
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while(!lock.compareAndSet(false, true)) {
//            Busy-wait until the lock is acquired
            /*
            * when there's thread holding lock -> real value: true -> false -> true -> retrying
            * when there's no thread holding lock -> real value: false -> true -> false
            *
            * */
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
