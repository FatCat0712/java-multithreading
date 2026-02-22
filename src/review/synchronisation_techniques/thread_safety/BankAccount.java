package review.synchronisation_techniques.thread_safety;

public class BankAccount {
    private int balance;
    private final Object balanceLock = new Object();

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount");
            return;
        }

        synchronized (balanceLock) {
            balance += amount;
            System.out.println("Deposit " + amount + ", " + " new balance: " + balance);
        }
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            return;
        }

        synchronized (balanceLock) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrew " + amount + ", new balance: " + balance);
            } else {
                System.out.println("Insufficient funds for withdrawal");
            }
        }
    }

    public int getBalance() {
        return balance;
    }

}
