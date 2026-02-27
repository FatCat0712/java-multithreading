package review.executor_service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 *   When the queue is full, the thread pool may reject tasks. Java provides four rejection policies in the ThreadPoolExecutor class:
 *   AbortPolicy (default): Throws a RejectedExecutionException
 *   CallerRunsPolicy: The calling thread executes the task
 *   DiscardPolicy: Silently discards the rejected task
 *   DiscardOldestPolicy: Discards the oldest task in the queue
 *
 * */
public class RejectionPolicyExample {
    public static void main(String[] args) {
//        Create a thread pool with a bounded queue and a rejection policy
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2
                , 4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

//        submit task to the pool
        for(int i = 1; i <= 10; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Executing Task " + taskId + " by " + Thread.currentThread().getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
