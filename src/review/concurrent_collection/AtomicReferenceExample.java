package review.concurrent_collection;


import java.util.concurrent.atomic.AtomicReference;

class Account {
    private String name;

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class AtomicReferenceExample {
    public static void main(String[] args) {
        AtomicReference<Account> account = new AtomicReference<>(new Account("John"));

        Runnable task = () -> {
            account.set(new Account(Thread.currentThread().getName()));
            System.out.println("Updated by: " + account.get().getName());
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final Account Holder: " + account.get().getName());

    }
}
