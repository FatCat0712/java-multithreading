package review.test_debugging;

public class RaceConditionExample {
    private int counter = 0;

    public synchronized void increment() {
        System.out.println(Thread.currentThread().getName() + " -Before Increment: " + counter);
        counter++;
        System.out.println(Thread.currentThread().getName() + " -After Increment: " + counter);
    }

    public static void main(String[] args) throws InterruptedException {
        RaceConditionExample example = new RaceConditionExample();
        Runnable task = () -> {
            for(int i = 0; i < 1_000_000; i++) {
                example.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

    }
}
