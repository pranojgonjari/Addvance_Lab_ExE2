package lab7.probstate2;

import java.io.*;
import java.util.Scanner;

public class ScannerClassDemo {

    public static void main(String[] args) {
        writeToConsoleFileUsingScannerClass();
    }

    private static void writeToConsoleFileUsingScannerClass() {
        try {
            System.out.println("Enter a message for Scanner class demo:");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter("console.txt", true))) {
                writer.println(message);
            }

            System.out.println("Message written to console.txt file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

