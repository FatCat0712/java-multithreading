package concurrency.executorservice;

import java.util.concurrent.*;

public class ExecutorServiceExample1 {
    public static void main(String[] args) {
        int corePoolSize = 10;
        int maxPoolSize = 20;
        long keepAliveTime = 3000;

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(120)
        );

        threadPoolExecutor = Executors.newFixedThreadPool(3);

        ExecutorService sheduledExecutorService =
                new ScheduledThreadPoolExecutor(corePoolSize);

        sheduledExecutorService = Executors.newScheduledThreadPool(10);



    }
}
