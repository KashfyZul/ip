import java.util.Scanner;

public class Meow {
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

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("_____________________________________");
            System.out.println(line);
            System.out.println("_____________________________________");
            line = in.nextLine();
        }
        System.out.println("_____________________________________");
        System.out.println("Meow. See you!");
        System.out.println("_____________________________________");
    }
}
