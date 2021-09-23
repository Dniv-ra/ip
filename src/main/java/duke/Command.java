package duke;

import duke.data.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class Command {
    /**
     * This function prints the list of tasks present. If empty, shows a prompt saying no tasks to list
     */
    public static void executeList() {
        ArrayList<Task> taskList = TaskList.getTaskList();
        if (taskList.size() == 0) {
            Ui.printEmptyListMessage();
        } else {
            Ui.printDivider();
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i));
            }
            Ui.printDivider();
        }
    }

    /**
     * Sets a given task as done based on the task index in the list.
     * If the index doesn't exist or invalid inputs are given shows error message
     * @param arguments an arrayList containing the user input string broken into its components
     */
    public static void executeDone(ArrayList<String> arguments) {
        try {
            ArrayList<Task> taskList = TaskList.getTaskList();
            int taskIndex = Integer.parseInt(arguments.get(1)) - 1;
            taskList.get(taskIndex).setDone();
            Ui.printDivider();
            System.out.println("Nice I've marked this task as done:");
            System.out.println(taskList.get(taskIndex));
            Ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskMessage();
        } catch (NumberFormatException e) {
            Ui.printInvalidArgumentMessage();
        }
    }

    /**
     * Adds a given task to the task list. If the input is missing parameters shows an error message
     * @param arguments an arrayList containing the user input string broken into its components
     */
    public static void executeAdd(ArrayList<String> arguments) {
        ArrayList<Task> taskList = TaskList.getTaskList();
        try {
            switch (arguments.get(0)) {
            case "todo":
                taskList.add(new ToDo(arguments.get(1)));
                break;
            case "deadline":
                taskList.add(new Deadline(arguments.get(1), arguments.get(2)));
                break;
            case "event":
                taskList.add(new Event(arguments.get(1), arguments.get(2)));
                break;
            default:
                break;
            }
            Ui.printAddTaskMessage();
        } catch (IndexOutOfBoundsException e) {
            Ui.printParameterErrorMessage();
        } catch (DateTimeParseException ex) {
            Ui.printTimeParseErrorMessage();
        }
    }

    /**
     * Gets text input from file, parses it into appropriate task and adds to the task list
     * @param inputFromFile the text input from the file
     */
    public static void executeLoad(String inputFromFile) {
        String[] arguments = inputFromFile.split("\\|");
        ArrayList<Task> taskList = TaskList.getTaskList();
        switch (arguments[0]) {
        case "T":
            if (arguments[1].equals("false")) {
                taskList.add(new ToDo(arguments[2]));
            } else {
                taskList.add(new ToDo(arguments[2], true));
            }
            break;
        case "D":
            if (arguments[1].equals("false")) {
                taskList.add(new Deadline(arguments[2], arguments[3]));
            } else {
                taskList.add(new Deadline(arguments[2], arguments[3], true));
            }
            break;
        case "E":
            if (arguments[1].equals("false")) {
                taskList.add(new Event(arguments[2], arguments[3]));
            } else {
                taskList.add(new Event(arguments[2], arguments[3], true));
            }
            break;
        default:
        }

    }

    /**
     * Deletes a given task as done based on the task index in the list.
     * If the index doesn't exist or invalid inputs are given shows error message
     * @param arguments an arrayList containing the user input string broken into its components
     */
    public static void executeDelete(ArrayList<String> arguments) {
        try {
            ArrayList<Task> taskList = TaskList.getTaskList();
            int taskIndex = Integer.parseInt(arguments.get(1)) - 1;
            taskList.get(taskIndex);
            Ui.printDivider();
            System.out.println("Noted. I've removed this task: ");
            System.out.println(taskList.get(taskIndex));
            taskList.remove(taskIndex);
            System.out.println("You now have " + taskList.size() + " items in the list.");
            Ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskMessage();
        } catch (NumberFormatException e) {
            Ui.printInvalidArgumentMessage();
        }
    }

}
