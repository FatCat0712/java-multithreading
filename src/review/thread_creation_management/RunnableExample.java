package review.thread_creation_management;

class ThreadCreation implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread is running and created by implementing runnable interface....");
    }
}



public class RunnableExample {
    public static void main(String[] args) {
        ThreadCreation obj = new ThreadCreation();
        Thread thread = new Thread(obj);
        Thread thread2 = new Thread(obj);
        Thread thread3 = new Thread(obj);
        Thread thread4 = new Thread(obj);
        thread.start();
    }
}
