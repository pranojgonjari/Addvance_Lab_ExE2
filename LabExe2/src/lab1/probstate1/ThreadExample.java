package lab1.probstate1;
import java.util.Scanner;

class IncrementThread extends Thread {
    private int start;

    IncrementThread(int start) {
        this.start = start;
    }

    public void run() {
        System.out.print("Thread-1:  ");
        for (int i = start; i < start + 10; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }
}

class MultiplicationThread implements Runnable {
    private int number;

    MultiplicationThread(int number) {
        this.number = number;
    }

    public void run() {
        System.out.print("Thread-2:  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(number * i + "  ");
        }
        System.out.println();
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter the second number: ");
        int num2 = scanner.nextInt();

        IncrementThread incrementThread = new IncrementThread(num1);
        Thread multiplicationThread = new Thread(new MultiplicationThread(num2));

        incrementThread.start();
        multiplicationThread.start();

        try {
            incrementThread.join();
            multiplicationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
