package lab5.probstate2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileContents {
    public static void main(String[] args) {
        String sourceFileName = "file1.txt";
        String destinationFileName = "file2.txt";

        try (FileReader reader = new FileReader(sourceFileName);
             FileWriter writer = new FileWriter(destinationFileName)) {

            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);
            }

            System.out.println("Contents copied from " + sourceFileName + " to " + destinationFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
