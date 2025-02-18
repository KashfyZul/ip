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

    public static void checkValidInput(String input) throws MeowException{
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
                printAnyTask(t);
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
        printAnyTask(taskToMark);
    }

    private static void printAnyTask(Task taskToMark) {
        // print out the task once marked/unmarked
        switch (taskToMark.getTaskType()) {
        case 'E':
            ((Event) taskToMark).printTask();
            break;
        case 'D':
            ((Deadline) taskToMark).printTask();
            break;
        case 'T':
            ((ToDo) taskToMark).printTask();
            break;
        default:
            break;
        }
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

        while (!line.equals(EXIT_PHRASE)) { // "bye" to exit
            line = getUserInput();

            if (Task.getTaskListSize() > MAX_LIST_SIZE) {
                System.out.println("Mreoww! This list is full!");
                System.out.println(LINE_SEPARATOR);
                continue;
            } else if (line.equals("list")) {
                listAllTasks(list);
                System.out.println(LINE_SEPARATOR);
                continue;
            } else if (line.contains("mark")) {
                markOrUnmark(line, list);
                System.out.println(LINE_SEPARATOR);
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

            System.out.println("You currently have " + Task.getTaskListSize() + " task(s) in the list");
            System.out.println(LINE_SEPARATOR);

        }
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

    private static void makeNewToDo(String line, Task[] list) {
        Task currTask;
        int taskNameStart = "todo ".length();
        String taskName = line.substring(taskNameStart);
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

        System.out.println("Meow. See you!");
        System.out.println(LINE_SEPARATOR);
    }
}
