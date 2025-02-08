import java.util.Scanner;

public class Meow {

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
                System.out.println(Integer.toString(t.getTaskNumber()) + ".[" + t.getStatusIcon() + "] " + t.getTaskName());
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

        System.out.println("[" + taskToMark.getStatusIcon() + "] " + taskToMark.getTaskName());
    }

    public static void addTask(String line, Task currTask, Task[] list) {
        System.out.println(LINE_SEPARATOR);
        list[Task.getTaskListSize() - 1] = currTask;
        System.out.println("added: " + line);
    }

    public static void taskList(Task[] list) {
        System.out.println("Type to add items to the list! add tuna to exit!");
        String line = "";
        Task currTask;

        while (!line.equals(EXIT_PHRASE)) { // "bye" to exit
            line = getUserInput();
            currTask = new Task(line);
            if (Task.getTaskListSize() > MAX_LIST_SIZE) {
                System.out.println("Mreoww! This list is full!");
                return;
            } else if (line.equals("list")) {
                listAllTasks(list);
            } else if (line.contains("mark")) {
                markOrUnmark(line, list);
            } else {
                addTask(line, currTask, list);
            }
            System.out.println(LINE_SEPARATOR);

        }
    }

    public static void openingMsg() {
        String meow = " /\\_/\\ \n"
                + "( o.o ) \n"
                + " > ^ < \n";

        System.out.println("Hello from\n \n" + meow + CHATBOT_NAME);
        System.out.println("Please leave some tuna before u leave. Meow");
        System.out.println(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        openingMsg();
        Task[] list = new Task[100];
        taskList(list);

        System.out.println(LINE_SEPARATOR);
        System.out.println("Meow. See you!");
        System.out.println(LINE_SEPARATOR);
    }
}
