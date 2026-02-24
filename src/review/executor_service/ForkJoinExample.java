package review.executor_service;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {
    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 1000;
        private final int[] numbers;
        private final int start, end;

        SumTask(int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if(end - start <= THRESHOLD) {
                long sum = 0;
                for(int i = start; i < end; i++) {
                    sum += numbers[i];
                }
                return sum;
            }

            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(numbers, start, mid);
            SumTask rightTask = new SumTask(numbers, mid, end);


            leftTask.fork();

            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[10_000_000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();

        SumTask task = new SumTask(numbers, 0 , numbers.length);
        long result = pool.invoke(task);

        System.out.println("Sum: " + result);
    }
}
