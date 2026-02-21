package review.thread_creation_management.join;

class MyThread5 extends Thread {
    @Override
    public void run() {
        for(int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("Thread: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class JoinExample {
    public static void main(String[] args) {
        MyThread5 thread = new MyThread5();
        thread.start();

//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//           e.printStackTrace();
//        }

        System.out.println("Main thread execution resumes after thread finishes. ");
    }
}
