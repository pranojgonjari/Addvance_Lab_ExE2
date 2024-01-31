package lab7.probstate4;

import java.io.*;

public class ReadEmployeeNames {

    public static void main(String[] args) {
        readEmployeeNamesWithPattern();
    }

    private static void readEmployeeNamesWithPattern() {
        try (BufferedReader reader = new BufferedReader(new FileReader("emp.txt"))) {
            System.out.println("Employee names starting with characters Aa-Dd:");
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.matches("^[A-Da-d].*")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
