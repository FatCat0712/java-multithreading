package review.thread_creation_management.platform_thread;

public class PlatformThreadsExample {
    public static void main(String[] args) {
        int numberOfThreads = 1000099; // Number ò threads to create

        for(int i = 0; i < numberOfThreads; i++) {
            int threadId = i; // Capture the current value of i for each thread
            Thread thread = new Thread(() -> {
                System.out.println("Thread ID: " + threadId + " is running");
                try {
//                    Simulate some work by making the thread sleep
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread ID: "  + threadId + " has completed");
            });
            thread.start();
        }

        System.out.println("All threads have been started.");
    }
}
