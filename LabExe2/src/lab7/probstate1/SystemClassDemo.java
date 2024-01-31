package lab7.probstate1;

import java.io.*;

public class SystemClassDemo {

    public static void main(String[] args) {
        writeToConsoleFileUsingSystemClass();
    }

    private static void writeToConsoleFileUsingSystemClass() {
        try {
            System.out.println("Enter a message for System class demo:");
            String message = new BufferedReader(new InputStreamReader(System.in)).readLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter("console.txt", true))) {
                writer.println(message);
            }

            System.out.println("Message written to console.txt file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

