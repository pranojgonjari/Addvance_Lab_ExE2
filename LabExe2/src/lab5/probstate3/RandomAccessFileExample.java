package lab5.probstate3;

import java.io.RandomAccessFile;

public class RandomAccessFileExample {
    public static void main(String[] args) {
        String fileName = "file1.txt";

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            // Read File Information
            System.out.println("File Information:");
            System.out.println("File Length: " + randomAccessFile.length());
            System.out.println("File Pointer: " + randomAccessFile.getFilePointer());

            // Write to File
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeBytes("\nAppended Text");
            System.out.println("Text appended to " + fileName);

            // Read Updated File Information
            System.out.println("Updated File Information:");
            System.out.println("File Length: " + randomAccessFile.length());
            System.out.println("File Pointer: " + randomAccessFile.getFilePointer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
