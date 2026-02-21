package review.thread_creation_management.virtual_threads;

import java.util.List;

public class VirtualThreadInLoops {
    public static void main(String[] args) throws InterruptedException {
        List<String> tasks = List.of("Task1", "Task2", "Task3", "Task4");

        for(int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i);
            Thread.ofVirtual().name("VirtualThread - " + (i+1)).start(() -> {
                System.out.println("Executing " + task + " in virtual thread " + Thread.currentThread().getName());
            });
        }

        Thread.sleep(100);
        System.out.println("Main thread completed");
    }
}
