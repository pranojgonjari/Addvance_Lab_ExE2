package lab3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private int balance;
    private final Lock lock;

    public Account(int initialBalance) {
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " Deposited: " + amount + ", New Balance: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " Withdrawn: " + amount + ", New Balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " Insufficient funds for withdrawal.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class TransactionThread extends Thread {
    private final Account account;
    private final int amount;
    private final boolean isDeposit;

    public TransactionThread(Account account, int amount, boolean isDeposit) {
        this.account = account;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    public void run() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Account account = new Account(1000);

        // Creating multiple threads for concurrent deposit and withdraw operations
        Thread depositThread1 = new TransactionThread(account, 200, true);
        Thread depositThread2 = new TransactionThread(account, 300, true);
        Thread withdrawThread1 = new TransactionThread(account, 150, false);
        Thread withdrawThread2 = new TransactionThread(account, 250, false);

        // Start all threads
        depositThread1.start();
        depositThread2.start();
        withdrawThread1.start();
        withdrawThread2.start();

        // Wait for all threads to finish
        try {
            depositThread1.join();
            depositThread2.join();
            withdrawThread1.join();
            withdrawThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the final balance
        System.out.println("Final Balance: " + account.getBalance());
    }
}
