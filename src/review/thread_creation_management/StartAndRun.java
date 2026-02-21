package review.thread_creation_management;

class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Thread is Running ...");
    }
}

public class StartAndRun {
    public static void main(String[] args) {
        MyThread2 thread = new MyThread2();
        thread.start();
    }
}
