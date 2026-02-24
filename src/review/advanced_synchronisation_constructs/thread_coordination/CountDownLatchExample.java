package review.advanced_synchronisation_constructs.thread_coordination;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

//        Worker threads
        for (int i = 1; i <= 3; i++) {
            new Thread(new Worker(i, latch)).start();
        }

        try {
            latch.await(); // Main threads waits until latch reaches 0
            System.out.println("All task completed. Proceeding to the next step");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Worker implements Runnable {
    private final int taskNumber;
    private final CountDownLatch latch;

    public Worker(int taskNumber, CountDownLatch latch) {
        this.taskNumber = taskNumber;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + taskNumber + " started");
            Thread.sleep(3000);
            System.out.println("Task " + taskNumber + " completed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            latch.countDown(); // Each task decreases the count by 1
        }
    }
}
