package review.concurrent_collection.compare_swap;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAProblemDemo {
    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(100,0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           int stamp = atomicStampedRef.getStamp();
           Integer value = atomicStampedRef.getReference();
            System.out.println("Thread initial value: " + value + ", initial stamp: " + stamp);

            // simulating a delay to cause an ABA problem
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

            boolean result = atomicStampedRef.compareAndSet(value, value + 10, stamp, stamp + 1);

            System.out.println("Thread1 CAS result: " + result);
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int stamp = atomicStampedRef.getStamp();
            atomicStampedRef.compareAndSet(atomicStampedRef.getReference(), atomicStampedRef.getReference() + 10, stamp, stamp + 1);
            System.out.println("Thread2 increments value: " + atomicStampedRef.getReference() + ", new stamp: " + atomicStampedRef.getStamp());

            atomicStampedRef.compareAndSet(atomicStampedRef.getReference(), atomicStampedRef.getReference() - 10, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("Thread2 decrements value: " + atomicStampedRef.getReference() + ", new stamp: " + atomicStampedRef.getStamp());
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value: " + atomicStampedRef.getReference() + ", final stamp: " + atomicStampedRef.getStamp());


    }
}
