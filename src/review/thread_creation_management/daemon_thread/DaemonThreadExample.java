package review.thread_creation_management.daemon_thread;

class MyThread10 extends Thread {
    public void run() {
        if(Thread.currentThread().isDaemon()) {
            System.out.println(Thread.currentThread().getName() + " is daemon thread");
        }
        else {
            System.out.println(Thread.currentThread().getName() + " is a user/non thread");
        }
    }
}

public class DaemonThreadExample {
    public static void main(String[] args) {
        MyThread10 thread1 = new MyThread10();
        MyThread10 thread2 = new MyThread10();

        thread1.setDaemon(true);
        thread1.setName("Daemon-Thread");

        thread2.setName("User-Thread");

        // must set daemon before the thread starts


        thread1.start();
//        thread1.setDaemon(false);

        thread2.start();
    }
}
