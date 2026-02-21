package review.thread_creation_management.get_set_name_thread;

class MyThread9 extends Thread {
    public void run() {
        System.out.println("Thread running : " + Thread.currentThread().getName());
    }
}

public class GetNameSetNameExample {
    public static void main(String[] args) {
        MyThread9 thread = new MyThread9();
        MyThread9 thread1 = new MyThread9();
        MyThread9 thread2 = new MyThread9();

        thread.setName("Worker - 1");
        thread1.setName("Worker - 2");
        thread2.setName("Worker - 3");


        thread.start();
        thread1.start();
        thread2.start();
    }
}
