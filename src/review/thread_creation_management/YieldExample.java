package review.thread_creation_management;

class MyThread7 extends Thread {
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is running. Iteration: " + i);
            Thread.yield();
        }
    }
}


public class YieldExample {
    public static void main(String[] args) {
        MyThread7 thread1 = new MyThread7();
        MyThread7 thread2 = new MyThread7();

        thread1.start();
        thread2.start();
    }

}
