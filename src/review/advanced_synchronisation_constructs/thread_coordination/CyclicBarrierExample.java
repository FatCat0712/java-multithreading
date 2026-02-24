package review.advanced_synchronisation_constructs.thread_coordination;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("All threads reached the barrier. Moving to the next stage");
        });

        for (int i = 1; i <= 3; i++) {
            new Thread(new Task(i, barrier)).start();
        }
    }
}

class Task implements Runnable {
    private final int taskNumber;
    private final CyclicBarrier barrier;

    public Task(int taskNumber, CyclicBarrier barrier) {
        this.taskNumber = taskNumber;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + taskNumber + " working...");
            Thread.sleep(1000); // Simulate work
            System.out.println("Task " + taskNumber + " waiting at barrier");
            barrier.await(); // Threads wait here
            System.out.println("Task " + taskNumber + " passed the barrier");
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
