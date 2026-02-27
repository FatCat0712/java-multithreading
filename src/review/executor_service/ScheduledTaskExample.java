package review.executor_service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskExample {
    public static void main(String[] args) {
//        Create a scheduled thread pool with 2 threads
        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2)) {

//            Schedule a simple task with a delay
//            Runnable task = () -> System.out.println("Task executed at: " + System.currentTimeMillis());
//            scheduler.schedule(task, 5, TimeUnit.SECONDS);

//                Schedule the task to run every 2 seconds with an initial delay of 1 second
//            Runnable task = () -> System.out.println("Recurring task executed at: " + System.currentTimeMillis());
//            scheduler.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);


            Runnable task = () -> {
                System.out.println("task executed at "+ System.currentTimeMillis());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };

//            Schedule a task with a fixed delay of 2 seconds
            scheduler.scheduleWithFixedDelay(task, 1,2, TimeUnit.SECONDS);

//          Allow the scheduler to run for 10 seconds, then shut down
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


//            shutdown the scheduler
            scheduler.shutdown();
        }


    }
}
