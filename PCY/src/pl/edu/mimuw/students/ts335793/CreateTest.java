package pl.edu.mimuw.students.ts335793;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class CreateTest {

    private static String fileURI;

    private static void parseParameters(String[] args) {
        try {
            fileURI = args[0];
        } catch (Exception exception) {
            System.out.println("Usage: CreateTest <file path>");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        parseParameters(args);

        Configuration configuration = new Configuration();

        FileSystem fileSystem;
        try {
            fileSystem = FileSystem.get(configuration);
        } catch (IOException exception) {
            System.out.println("Failed to get file system.");
            System.out.println(exception.getLocalizedMessage());
            System.exit(1);
            return;
        }

        Path filePath = new Path(fileURI);

        FSDataOutputStream outputStream;
        try {
            outputStream = fileSystem.create(filePath);
        } catch (IOException exception) {
            System.out.println("Failed to create input stream.");
            System.out.println(exception.getLocalizedMessage());
            System.exit(1);
            return;
        }

        Integer[] ids = { 1, 2, 3, -1, 1, 2, -1 };

        for (Integer id : ids) {
            try {
                outputStream.writeInt(id);
            } catch (IOException exception) {
                System.out.println("Failed to write to output stream.");
                System.out.println(exception.getLocalizedMessage());
                System.exit(1);
                return;
            }
        }
    }
}
