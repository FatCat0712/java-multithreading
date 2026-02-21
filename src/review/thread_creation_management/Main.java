package review.thread_creation_management;

class MyThread1 extends Thread {

    public void interrupt(){

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Thread completed");
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        MyThread1 thread = new MyThread1();
        thread.start();
        thread.interrupt();
    }
}
