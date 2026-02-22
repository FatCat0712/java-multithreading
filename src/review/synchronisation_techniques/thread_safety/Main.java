package review.synchronisation_techniques.thread_safety;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Thread t1 = new Thread(() -> {
           for(int i = 0; i < 5; i++) {
               account.deposit(100);
               try {
                   Thread.sleep(50);
               } catch (InterruptedException e) {
                //
                   e.printStackTrace();
               }
           }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                account.withdraw(50);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    //
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

    }
}
