import meow.exception.MeowException;
import meow.task.*;

import java.io.*;

import meow.commands.Commands;
import meow.task.MakeTasks;
import meow.ui.Ui;



public class Meow {

    public static final String EXIT_PHRASE = "add tuna";
    public static final String LINE_SEPARATOR = "_____________________________________";
    public static final String CHATBOT_NAME = "Meow";

    private static Storage storage;
    public static TaskList list;
    private static Ui ui;

    public Meow() {
        ui = new Ui();
        storage = new Storage();
        list = new TaskList();
    }

    /**
     * Accepts user input and provides various output based on user input
     *
     */
    public static void taskList() {
        System.out.println("Type to add items to the list! add tuna to exit!");
        String taskType;
        String line = "";
        Task currTask;

        try {
            ui.readTask(storage.readFile(), list);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Output txt file not found");
        }

        System.out.println(LINE_SEPARATOR);

        while (true) { // "bye" to exit
            line = ui.getUserInput();

            if (line.equals("list")) {
                Commands.listAllTasks(list);
                System.out.println(LINE_SEPARATOR);
                if (ui.updateFileWithCatch(storage, list)) {
                    break;
                }
                continue;
            } else if (line.contains("mark")) {
                Commands.markOrUnmark(line, list);
                System.out.println(LINE_SEPARATOR);
                if (ui.updateFileWithCatch(storage, list)) {
                    break;
                }
                continue;
            } else if (line.startsWith("delete")) {
                Commands.delete(line, list);
                ui.printHowManyTasks();
                if (ui.updateFileWithCatch(storage, list)) {
                    break;
                }
                continue;
            } else if (line.startsWith("find")) {
                TaskList matchingList = Commands.find(line, list);
                Commands.listAllTasks(matchingList);
                System.out.println(LINE_SEPARATOR);
                if (ui.updateFileWithCatch(storage, list)) {
                    break;
                }
                continue;
            } else if (line.equals(EXIT_PHRASE)) {
                System.out.println("Meow. See you!");
                System.out.println(LINE_SEPARATOR);
                return;
            }

            // handle erroneous input
            try {
                ui.checkValidInput(line);
            } catch (MeowException e) {
                System.out.println("Caught exception: " + e.getMessage());
                System.out.println(LINE_SEPARATOR);
                continue;
            }
            // make new task based on user description
            if (line.startsWith("deadline")) {
                MakeTasks.makeNewDeadline(line, list);
            } else if (line.startsWith("event")) {
                MakeTasks.makeNewEvent(line, list);
            } else if (line.startsWith("todo")) {
                MakeTasks.makeNewToDo(line, list);
            } else {
                System.out.println("Meow? I don't understand that");
                System.out.println(("Begin your task with 'todo', 'event' or 'deadline'"));
                continue;
            }
            if (ui.updateFileWithCatch(storage, list)) {
                break;
            }
            ui.printHowManyTasks();
            System.out.println(LINE_SEPARATOR);
        }
    }

    /**
     * Initialises program, reads data from output file
     */
    public void runMeow() {
        ui.openingMsg();
        try {
            storage.readFile();
        } catch (FileNotFoundException fnfe) {
            System.out.println("output file not found");
        }
        taskList();
    }

    public static void main(String[] args) {
        new Meow().runMeow();
    }
}
