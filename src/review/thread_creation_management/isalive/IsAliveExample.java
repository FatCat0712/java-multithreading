package review.thread_creation_management.isalive;

class MyThread8 extends Thread {
    public void run () {
        for(int i = 0; i <= 3; i++) {
            System.out.println("Thread is running: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class IsAliveExample {
    public static void main(String[] args) {
        MyThread8 thread = new MyThread8();
        thread.start();

        while(thread.isAlive()) {
            System.out.println("Thread is still running....");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Thread has finished execution.");
    }
}
