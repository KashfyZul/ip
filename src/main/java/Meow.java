import meow.exception.MeowException;
import meow.task.Deadline;
import meow.task.Event;
import meow.task.Task;
import meow.task.ToDo;

import java.io.*;
import java.util.Scanner;


public class Meow {

//    static final String outputFilePath = "src/main/java/meow/data/meowOutput.txt";
    static final String outputFilePath = "C:/Users/cyber/Documents/ip/src/main/java/meow/data/meowOutput.txt";
//    static String output = "";

    public static final String EXIT_PHRASE = "add tuna";
    public static final String LINE_SEPARATOR = "_____________________________________";
    public static final int MAX_LIST_SIZE = 100;
    public static final String CHATBOT_NAME = "Meow";

    public static void echo(String line, Scanner in) {
        while (!line.equals(EXIT_PHRASE)) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(line);
            System.out.println(LINE_SEPARATOR);
            line = in.nextLine();
        }
    }

    public static void checkValidInput(String input) throws MeowException {
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


    public static String getUserInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static void listAllTasks(Task[] list) {
        if (Task.getTaskListSize() == 0) {
            System.out.println("This list empty! YEET");
        } else {
            System.out.println("Here are your tasks!");
            for (Task t : list) {
                if (t == null) {
                    break;
                }
                System.out.print(Integer.toString(t.getTaskNumber()) + ".");
                System.out.println(stringAnyTask(t));
            }
        }
    }

    public static void markOrUnmark(String line, Task[] list) {
        int taskNumber = Integer.parseInt(line.substring(line.length() - 1));
        Task taskToMark = list[taskNumber - 1];
        if (line.startsWith("mark")) {
            if (taskToMark.isDone()) {
                System.out.println("This task is already done!");
                return;
            }
            taskToMark.setDone();
            System.out.println("Meow meow this task is done");
        } else if (line.startsWith("unmark")) {
            if (!taskToMark.isDone()) {
                System.out.println("This task is already set as not done");
                return;
            }
            taskToMark.setNotDone();
            System.out.println("meowwww didn't you complete this task?");
        }
        System.out.println(stringAnyTask(taskToMark));
    }

    public static void delete(String line, Task[] list) {
        int taskNumber = Integer.parseInt(line.substring(line.length() - 1)) - 1;
        Task taskToMark = list[taskNumber];
        System.out.println("Okie, this task has been removed: ");
        System.out.println(stringAnyTask(taskToMark));

        while (list[taskNumber] != null) {
            list[taskNumber].setTaskNumber(list[taskNumber].getTaskNumber() - 1);
            list[taskNumber] = list[taskNumber + 1];
            taskNumber++;
        }
        Task.decrementTaskListSize();
        System.out.println(LINE_SEPARATOR);
    }


//    private static void printAnyTask(Task taskToMark) {

    private static String stringAnyTask(Task taskToMark) {
        // print out the task once marked/unmarked
        switch (taskToMark.getTaskType()) {
        case 'E':
            return ((Event) taskToMark).toString();
        case 'D':
            return ((Deadline) taskToMark).toString();
        case 'T':
            return ((ToDo) taskToMark).toString();
        default:
            break;
        }
        return "";
    }

    public static void addTask(String line, Task currTask, Task[] list) {
        System.out.println(LINE_SEPARATOR);
        list[Task.getTaskListSize() - 1] = currTask;
        System.out.println("added: " + line);
    }

    public static void taskList(Task[] list) {
        System.out.println("Type to add items to the list! add tuna to exit!");
        String taskType;
        String line = "";
        Task currTask;

        try {
            readTask(readFile(), list);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Output txt file not found");
        }
        while (!line.equals(EXIT_PHRASE)) { // "bye" to exit
            line = getUserInput();

            if (Task.getTaskListSize() > MAX_LIST_SIZE) {
                System.out.println("Mreoww! This list is full!");
                System.out.println(LINE_SEPARATOR);
                if (updateFileWithCatch(list)) {
                    break;
                }
                continue;
            } else if (line.equals("list")) {
                listAllTasks(list);
                System.out.println(LINE_SEPARATOR);
                if (updateFileWithCatch(list)) {
                    break;
                }
                continue;
            } else if (line.contains("mark")) {
                markOrUnmark(line, list);
                System.out.println(LINE_SEPARATOR);
                if (updateFileWithCatch(list)) {
                    break;
                }
                continue;
            } else if (line.startsWith("delete")) {
                delete(line, list);
                printHowManyTasks();
                if (updateFileWithCatch(list)) {
                    break;
                }
                continue;
            }

            // handle erroneous input
            try {
                checkValidInput(line);
            } catch (MeowException e) {
                System.out.println("Caught exception: " + e.getMessage());
                System.out.println(LINE_SEPARATOR);
                continue;
            }

            // make new task based on user description
            if (line.startsWith("deadline")) {
                makeNewDeadline(line, list);
            } else if (line.startsWith("event")) {
                makeNewEvent(line, list);
            } else if (line.startsWith("todo")) {
                makeNewToDo(line, list);
            } else {
                System.out.println("Meow? I don't understand that");
                System.out.println(("Begin your task with 'todo', 'event' or 'deadline'"));
                continue;
            }


            if (updateFileWithCatch(list)) {
                break;
            }

            printHowManyTasks();
            System.out.println(LINE_SEPARATOR);

        }
    }

    private static boolean updateFileWithCatch(Task[] list) {
        try {
            updateFile(list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private static void printHowManyTasks() {
        System.out.println("You currently have " + Task.getTaskListSize() + " task(s) in the list");
    }

    private static void makeNewEvent(String line, Task[] list) {
        Task currTask;
        int taskNameStart = "event ".length();
        int taskNameEnd = line.indexOf('/');
        String taskName = line.substring(taskNameStart, taskNameEnd);
        String from = line.substring(taskNameEnd + "from ".length(), line.lastIndexOf('/') - 1);
        int toStart = line.lastIndexOf('/') + "to ".length();
        String to = line.substring(toStart);
        currTask = new Event(taskName, from, to);
        list[Task.getTaskListSize() - 1] = currTask;
    }

    private static void makeExistingEvent (String line, Task[] list) {
        Task currTask;
        int taskNameStart = "[E][ ] ".length();
        int taskNameEnd = line.indexOf('(');
        String taskName  = line.substring(taskNameStart, taskNameEnd);
        String from = line.substring(line.indexOf(':') + 1, line.lastIndexOf(':') - 4);
        String to = line.substring(line.lastIndexOf(':') + 1, line.lastIndexOf(')'));
        currTask = new Event(taskName, from, to);
        list[Task.getTaskListSize() - 1] = currTask;
    }

    private static void makeNewToDo(String line, Task[] list) {
        Task currTask;
        int taskNameStart = "todo ".length();
        String taskName = line.substring(taskNameStart);
        currTask = new ToDo(taskName);
        list[Task.getTaskListSize() - 1] = currTask;
    }

    private static void makeExistingToDo(String line, Task[] list) {
        Task currTask;
        int taskNameStart = "[T][ ] ".length();
        String taskName  = line.substring(taskNameStart);
        currTask = new ToDo(taskName);
        list[Task.getTaskListSize() - 1] = currTask;
    }

    private static void makeNewDeadline(String line, Task[] list) {
        Task currTask;
        int taskNameStart = "deadline ".length();
        int taskNameEnd = line.indexOf('/');
        String taskName = line.substring(taskNameStart, taskNameEnd);
        String by = line.substring(taskNameEnd + "by ".length());
        currTask = new Deadline(taskName, by);
        list[Task.getTaskListSize() - 1] = currTask;
    }

    private static void makeExistingDeadline(String line, Task[] list) {
        Task currTask;
        int taskNameStart = "[D][ ] ".length();
        int taskNameEnd = line.indexOf('(');
        String taskName  = line.substring(taskNameStart, taskNameEnd);
        String by = line.substring(line.indexOf(':') + 1, line.indexOf(')'));
        currTask = new Deadline(taskName, by);
        list[Task.getTaskListSize() - 1] = currTask;
    }

    public static void openingMsg() {
        String meow = " /\\_/\\ \n"
                + "( o.o ) \n"
                + " > ^ < \n";

        System.out.println("Hello from\n \n" + meow + CHATBOT_NAME);
        System.out.println("Please leave some tuna before u leave. Meow");
        System.out.println(LINE_SEPARATOR);
    }

    // converts the string from output file into tasks
    private static void readTask(String fileTxt, Task[] list) {
//        String subString = fileTxt;
        int beginDex = 0;
        int endDex = 0;
        String[] tasks = fileTxt.split("\n");
        for (String task : tasks) {
            // insert code to read each line

            if (task.charAt(1) == 'D') {
                makeExistingDeadline(task, list);
            } else if (task.charAt(1) == 'E') {
                makeExistingEvent(task, list);
            } else if (task.charAt(1) == 'T') {
                makeExistingToDo(task, list);
            }
        }
    }

    private static String readFile() throws FileNotFoundException {
        String fileTxt = "";
        try {
            FileReader in = new FileReader(outputFilePath);
            // read output file char by char
            int currChar;
            while ((currChar = in.read()) != -1) {
                fileTxt = fileTxt + (char)currChar;
            }
            System.out.println(fileTxt);
            in.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File " + outputFilePath + " is not found");
        } catch (IOException ioe) {
            System.out.println("I/O Exception error");
        }
        return fileTxt;
    }


    private static void updateFile(Task[] list) throws IOException {
        FileWriter out = new FileWriter(outputFilePath);
        String output = updateOutputList(list);
        out.write(output);
        out.close();
    }

    private static String updateOutputList(Task[] list) {
        String output = "";
        for (Task task : list) {
            if (task != null) {
                output += task.toString() + "\n";
            }
        }
        return output;
    }

//    private static String readFile(String filePath, int lineCount) {
//        try (Scanner scanner = new Scanner(new File(filePath))) {
//            while (scanner.hasNextLine()) {  // Read each line
//                System.out.println(scanner.nextLine());
//            }
//        } catch (IOException e) {
//            System.out.println("File not found or error reading file.");
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        openingMsg();
        try {
            readFile();
        } catch (FileNotFoundException fnfe) {
            System.out.println("output file not found");
        }
        Task[] list = new Task[100];
        taskList(list);

        System.out.println("Meow. See you!");
        System.out.println(LINE_SEPARATOR);
    }
}
