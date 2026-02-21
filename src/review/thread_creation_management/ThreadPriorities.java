package review.thread_creation_management;

// setPriority() & getPriority()
class MyThread6 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Priority: " + Thread.currentThread().getPriority());
    }
}


public class ThreadPriorities {
    public static void main(String[] args) {
        MyThread6 thread0 = new MyThread6();
        MyThread6 thread1 = new MyThread6();

        thread0.setPriority(Thread.MIN_PRIORITY);
        thread1.setPriority(Thread.MAX_PRIORITY);

        thread0.start();
        thread1.start();
    }
}
