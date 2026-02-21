package concurrency.virtual_threads;

import java.util.concurrent.*;

public class ExecutorServiceThreadsExample {
    public static void main(String[] args) {
        Future<String> future;
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            executor.submit(() -> {
                System.out.println("This is a Runnable that is executed by a virtual thread");
            });

            Callable<String> callable = () -> {
                System.out.println("Callable executed by virtual thread");
                return "Result from Callable";
            };

            future = executor.submit(callable);
        }

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }
}
