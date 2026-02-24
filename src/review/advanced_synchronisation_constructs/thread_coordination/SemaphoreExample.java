package review.advanced_synchronisation_constructs.thread_coordination;

import java.util.concurrent.Semaphore;

class SharedResource {
    private final Semaphore semaphore;

    public SharedResource(int slots) {
        this.semaphore = new Semaphore(slots);
    }

    public void accessResource(String threadName) {
        try {
            semaphore.acquire();
            System.out.println(threadName + " acquire access to the resource.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(threadName + " releasing resource.");
            semaphore.release();
        }
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {
       SharedResource resource = new SharedResource(3);

        for(int i = 1; i <= 5; i++) {
            final int threadNumber = i;
            new Thread(() -> {
                resource.accessResource("Thread " + threadNumber);
            }).start();
        }




    }
}
