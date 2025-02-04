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
    public static void addList(String[] list) {
        System.out.println("Type to add items to the list! add tuna to exit!");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int listCount = 0;

        while (!line.equals("add tuna")) { // "bye" to exit
            if (listCount > 100) {
                System.out.println("Mreoww! This list is full!");
                return;
            }
            else if (line.equals("list")) {
                if (listCount == 0) {
                    System.out.println("This list is empty! YEET");
                }
                else {
                    int itemCount = 1;
                    while (itemCount <= listCount) {
                        System.out.println(Integer.toString(itemCount) + ". " + list[itemCount - 1]);
                        itemCount++;
                    }
                }
            }
            else {
                System.out.println("_____________________________________");
                list[listCount] = line;
                listCount++;
                System.out.println("added: " + line);
            }
            System.out.println("_____________________________________");
            line = in.nextLine();
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
        String[] list = new String[100];


//        echo(line, in);
        addList(list);

        System.out.println("_____________________________________");
        System.out.println("Meow. See you!");
        System.out.println("_____________________________________");
    }
}
