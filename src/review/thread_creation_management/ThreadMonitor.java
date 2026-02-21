package review.thread_creation_management;

import java.util.ArrayList;
import java.util.List;

class TaskThread extends Thread {
    private String taskName;

    public TaskThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started.");
        try {
            Thread.sleep((int)(Math.random() * 5000));
        } catch (InterruptedException e) {
            System.out.println(taskName + " interrupted.");
        }
        System.out.println(taskName + " completed");
    }
}

public class ThreadMonitor {
    public static void main(String[] args) {
        List<TaskThread> threads = new ArrayList<>();
        threads.add(new TaskThread("Task 1"));
        threads.add(new TaskThread("Task 2"));
        threads.add(new TaskThread("Task 3"));
        threads.add(new TaskThread("Task 4"));
        threads.add(new TaskThread("Task 5"));

        for(TaskThread thread: threads) {
            thread.start();
        }

        boolean allThreadsCompleted;
        do {
            allThreadsCompleted = true;
            for(TaskThread thread : threads) {
                if(thread.isAlive()) {
                    allThreadsCompleted = false;
                    System.out.println(thread.getName() + " is still running ... ");
                }
            }

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                System.out.println("Monitoring Interrupted!.");
            }
        }while (!allThreadsCompleted);

        System.out.println("All tasks are completed");
    }
}


