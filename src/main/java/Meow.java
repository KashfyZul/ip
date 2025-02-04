import java.util.Scanner;

public class Meow {
    public static void echo(String line, Scanner in) {
        while (!line.equals("bye")) {
            System.out.println("_____________________________________");
            System.out.println(line);
            System.out.println("_____________________________________");
            line = in.nextLine();
        }
    }
    public static void addList(Task[] list) {
        System.out.println("Type to add items to the list! add tuna to exit!");

//        int listCount = 0;
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task currTask = new Task(line);

        while (!line.equals("add tuna")) { // "bye" to exit
            if (Task.getTaskListSize() > 100) {
                System.out.println("Mreoww! This list is full!");
                return;
            }
            else if (line.equals("list")) {
                if (Task.getTaskListSize() == 0) {
                    System.out.println("This list is empty! YEET");
                }
                else {
                    System.out.println("Here are your tasks!");
                    for (Task t: list) {
                        if (t == null) {
                            break;
                        }
                        System.out.println(Integer.toString(t.getTaskNumber()) + ".[" + t.getStatusIcon() + "] " + t.getTaskName());
                    }
                }
            }
            else if (line.startsWith("mark")) {
                int taskToMark = Integer.parseInt(line.substring(line.length() - 1));
                list[taskToMark - 1].setDone();
                System.out.println("Meow meow this task is done");
                System.out.println("[" + list[taskToMark - 1].getStatusIcon() + "] " + list[taskToMark - 1].getTaskName());
            }
            else if (line.startsWith("unmark")) {
                int taskToMark = Integer.parseInt(line.substring(line.length() - 1));
                list[taskToMark - 1].setNotDone();
                System.out.println("meowwww didn't you complete this task?");
                System.out.println("[" + list[taskToMark - 1].getStatusIcon() + "] " + list[taskToMark - 1].getTaskName());
            }
            else {
                System.out.println("_____________________________________");
                list[Task.getTaskListSize() - 1] = currTask;
                System.out.println("added: " + line);
            }
            System.out.println("_____________________________________");
            line = in.nextLine();
            currTask = new Task(line);
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String chatbotName = "Meow";

        String meow =" /\\_/\\ \n"
        + "( o.o ) \n"
        + " > ^ < \n";

        System.out.println("Hello from\n \n" + meow + chatbotName);
        System.out.println("Please leave some tuna before u leave. Meow");
        System.out.println("_____________________________________");
        Task[] list = new Task[100];


//        echo(line, in);
        addList(list);

        System.out.println("_____________________________________");
        System.out.println("Meow. See you!");
        System.out.println("_____________________________________");
    }
}
