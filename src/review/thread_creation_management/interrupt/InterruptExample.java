package review.thread_creation_management.interrupt;
class MyThread4 extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted while sleeping ...");
        }
    }
}


public class InterruptExample {
    public static void main(String[] args) {
        MyThread4 thread = new MyThread4();
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
