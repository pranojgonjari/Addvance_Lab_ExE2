package lab5.probstate1;

import java.io.File;

public class CheckFileAvailability {
    public static void main(String[] args) {
        String fileName = "file1.txt";
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println(fileName + " exists.");
            System.out.println("File Information:");
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Size: " + file.length() + " bytes");
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
        } else {
            System.out.println(fileName + " does not exist.");
        }
    }
}
