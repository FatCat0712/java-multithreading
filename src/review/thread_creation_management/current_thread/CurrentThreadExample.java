package review.thread_creation_management.current_thread;

// currentThread
class MyRunnable1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Current thread: " + Thread.currentThread().getName());
    }
}

public class CurrentThreadExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable1());
        Thread thread2 = new Thread(new MyRunnable1());

        thread1.setName("Worker - 1");
        thread2.setName("Worker - 2");

        thread1.start();
        thread2.start();


    }
}
