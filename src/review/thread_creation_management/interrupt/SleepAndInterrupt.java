package review.thread_creation_management.interrupt;

class MyThread3 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
        }
    }
}

public class SleepAndInterrupt {
    public static void main(String[] args) {
        MyThread3 thread0 = new MyThread3();
        MyThread3 thread1 = new MyThread3();
        thread0.start();
        thread1.start();
    }
}
