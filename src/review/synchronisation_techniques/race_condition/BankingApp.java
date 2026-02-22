package review.synchronisation_techniques.race_condition;

public class BankingApp {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        User user1 = new User(account, true, 100);
        User user2 = new User(account, false, 50);

        Thread thread1 = new Thread(user1, "User A");
        Thread thread2 = new Thread(user2, "User B");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
            System.out.println("Thread interrupted");
        }
        System.out.println("Final balance: " + account.getBalance());


    }
}
