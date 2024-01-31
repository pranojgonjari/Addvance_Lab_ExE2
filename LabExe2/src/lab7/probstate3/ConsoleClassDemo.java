package lab7.probstate3;

import java.io.*;

public class ConsoleClassDemo {

    public static void main(String[] args) {
        writeToConsoleFileUsingConsoleClass();
    }

    private static void writeToConsoleFileUsingConsoleClass() {
        Console console = System.console();

        if (console == null) {
            System.out.println("Console is not available.");
            return;
        }

        try {
            String message = console.readLine("Enter a message for Console class demo:");

            try (PrintWriter writer = new PrintWriter(new FileWriter("console.txt", true))) {
                writer.println(message);
            }

            System.out.println("Message written to console.txt file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

