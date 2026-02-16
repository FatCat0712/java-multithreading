package concurrency.executorservice;

import java.util.concurrent.*;

public class ExecutorServiceExample8 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> future = executorService.submit(newCallable("Task 1.1."));

        System.out.println(future.isDone());

        boolean mayInterrupt = true;
        boolean wasCancelled = future.cancel(mayInterrupt);
        System.out.println(wasCancelled);

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
        }catch (CancellationException e) {
            String message = "Cannot call Future.get() since task was cancelled";
            System.out.println(message);
        }

        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        executorService.shutdown();

    }

    private static Callable<String> newCallable(String msg) {
        return () -> Thread.currentThread().getName() + ": " + msg;
    }
}
