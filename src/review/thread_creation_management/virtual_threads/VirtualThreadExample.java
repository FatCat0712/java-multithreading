package review.thread_creation_management.virtual_threads;

public class VirtualThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread virtualThread = Thread.ofVirtual().name("MyVirtualThread").start(() -> {
            System.out.println("Virtual Thread running: " + Thread.currentThread().getName());
        });

        Thread.sleep(100);

        System.out.println("Main thread completed. ");
    }
}
