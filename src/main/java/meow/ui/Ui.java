package meow.ui;

import meow.exception.MeowException;

import java.io.IOException;
import java.util.Scanner;

import meow.task.MakeTasks;
import meow.task.Storage;
import meow.task.Task;
import meow.task.TaskList;

public class Ui {

    public static final String EXIT_PHRASE = "add tuna";
    public static final String LINE_SEPARATOR = "_____________________________________";
    public static final String CHATBOT_NAME = "Meow";

    private Scanner scanner;

    public Ui() {
        this.scanner =new Scanner(System.in);
    }


    public static void printHowManyTasks() {
        System.out.println("You currently have " + Task.getTaskListSize() + " task(s) in the list");
    }


    public static void openingMsg() {
        String meow = " /\\_/\\ \n"
                + "( o.o ) \n"
                + " > ^ < \n";

        System.out.println("Hello from\n \n" + meow + CHATBOT_NAME);
        System.out.println("Please leave some tuna before u leave. Meow");
        System.out.println(LINE_SEPARATOR);
    }



    public String getUserInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public void checkValidInput(String input) throws MeowException {
        String tasktype;
        int firstSpaceDex = input.indexOf(" ");

        // handle errors if there is no whitespace after the first word
        if (firstSpaceDex == -1) {
            if (input.equals("deadline") || input.equals("event") || input.equals("todo")) {
                throw new MeowException("Nyan, the description of a " + input + " cannot be empty :(");
            } else {
                // invalid input: neither deadline nor event nor todo
                throw new MeowException("Meow? I don't understand that");
            }
        }
        String firstWord = input.substring(0, firstSpaceDex);
        if (firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("todo")) {
            tasktype = firstWord;
        } else {
            // invalid input: neither deadline nor event nor todo
            throw new MeowException("Meow? I don't understand that");
        }
        String description = input.substring(firstSpaceDex);
        // empty strings or only whitespace strings will be returned as false
        if (description.isBlank()) {
            throw new MeowException("Nyan, the description of a " + tasktype + " cannot be empty :(");
        }
    }

    // converts the string from output file into tasks
    public static void readTask(String fileTxt, TaskList list) {
        if (fileTxt.equals("")) {
            return;
        }
        int beginDex = 0;
        int endDex = 0;
        String[] tasks = fileTxt.split("\n");
        for (String task : tasks) {
            // insert code to read each line
            if (task.charAt(1) == 'D') {
                MakeTasks.makeExistingDeadline(task, list);
            } else if (task.charAt(1) == 'E') {
                MakeTasks.makeExistingEvent(task, list);
            } else if (task.charAt(1) == 'T') {
                MakeTasks.makeExistingToDo(task, list);
            }
        }
    }


    public static boolean updateFileWithCatch(Storage storage, TaskList list) {
        try {
            storage.updateFile(list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }
}
