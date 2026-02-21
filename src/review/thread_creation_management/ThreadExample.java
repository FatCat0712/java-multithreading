package review.thread_creation_management;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running and created by extending thread class...");
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
