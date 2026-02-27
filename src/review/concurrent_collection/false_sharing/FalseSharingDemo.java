package review.concurrent_collection.false_sharing;

public class FalseSharingDemo {
    static class Counter {
        public volatile long counter1 = 0;
        private long padding1, padding2, padding3, padding4, padding5, padding6, padding7;
        public volatile long counter2 = 0;

    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task1 = () -> {
            for (int i = 0; i < 10_000_000; i++) {
                counter.counter1++;
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 10_000_000; i++) {
                counter.counter2++;
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        long startTime = System.nanoTime();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long endTime = System.nanoTime();

        System.out.println("Execution time: " + (endTime - startTime)/1_000_000 + " ms");

    }
}
