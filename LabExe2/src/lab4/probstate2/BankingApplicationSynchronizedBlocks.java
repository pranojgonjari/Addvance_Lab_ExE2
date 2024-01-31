package lab4.probstate2;

class Account {
    private int balance;

    public Account(int initialBalance) {
        this.balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public int deposit(int amount) {
        synchronized (this) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " Deposited: " + amount + ", New Balance: " + balance);
            return balance;
        }
    }

    public int withdraw(int amount) {
        synchronized (this) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " Withdrawn: " + amount + ", New Balance: " + balance);
                return amount;
            } else {
                System.out.println(Thread.currentThread().getName() + " Insufficient funds for withdrawal.");
                return 0;
            }
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

public class BankingApplicationSynchronizedBlocks {
    public static void main(String[] args) {
        Account account = new Account(1000);

        Thread depositThread1 = new TransactionThread(account, 200, true);
        Thread depositThread2 = new TransactionThread(account, 300, true);
        Thread withdrawThread1 = new TransactionThread(account, 150, false);
        Thread withdrawThread2 = new TransactionThread(account, 250, false);

        depositThread1.start();
        depositThread2.start();
        withdrawThread1.start();
        withdrawThread2.start();

        try {
            depositThread1.join();
            depositThread2.join();
            withdrawThread1.join();
            withdrawThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance: " + account.getBalance());
    }
}
