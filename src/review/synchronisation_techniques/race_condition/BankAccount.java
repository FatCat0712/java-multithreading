package review.synchronisation_techniques.race_condition;

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

//    Deposit method
    public synchronized void deposit(int amount) {
        if(amount > 0) {
            System.out.println(Thread.currentThread().getName() + " depositing " + amount);
            int newBalance = balance + amount;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
            balance = newBalance;
            System.out.println("Balance after deposit: " + balance);
        }
    }

//    withdraw method
    public synchronized void withdraw(int amount) {
        if(amount > 0 && balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
            int newBalance = balance - amount;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }

            balance = newBalance;
            System.out.println("Balance after withdrawal: " + balance);
        }
        else {
            System.out.println(Thread.currentThread().getName() + " cannot withdraw "
                    + amount + "due to  insufficient balance");
        }
    }


    public int getBalance() {
        return this.balance;
    }
}


