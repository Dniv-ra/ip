package duke.data;

import duke.Command;
import duke.Ui;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "tasks.txt";

    public static void write() {
        try {
            File newDirectory = new File(DIRECTORY_NAME);
            if(!newDirectory.exists()) {
                System.out.println("Creating directory " + DIRECTORY_NAME);
                newDirectory.mkdirs();
            }
            FileWriter writer = new FileWriter(DIRECTORY_NAME + File.separator + FILE_NAME);
            for(Task task : TaskList.getTaskList()) {
                writer.write(task.formatData() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Ui.printDivider();
            System.out.println("Unable to access file");
            Ui.printDivider();
        }
    }

    public static void load() {
        File newDirectory = new File(DIRECTORY_NAME);
        if(!newDirectory.exists()) {
            System.out.println("Creating directory " + DIRECTORY_NAME);
            newDirectory.mkdirs();
        }
        File fileToRead = new File(DIRECTORY_NAME + File.separator + FILE_NAME);
        try {
            Scanner s = new Scanner(fileToRead);
            while (s.hasNext()) {
                String in = s.nextLine();
                Command.executeLoad(in);
            }
        } catch (FileNotFoundException e) {
            try {
                System.out.println("Creating " + FILE_NAME);
                fileToRead.createNewFile();
            } catch (IOException ex) {
                Ui.printDivider();
                System.out.println("Unable to create file");
                Ui.printDivider();
            }
        }
    }
}