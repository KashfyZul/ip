package meow.task;

import meow.ui.Ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Contains methods to handle reading from and writing to the output text file
 */
public class Storage {

    String outputFilePath;
    TaskList tasklist;
    Ui ui;

    /**
     * Constructor for Storage class
     * @param outputFilePath to specify path to output file
     */
    public Storage(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        tasklist = new TaskList();
        ui = new Ui();
    }




    /**
     * Reads file and returns the text file as a String.
     *
     * @return text from output file as a String.
     * @throws FileNotFoundException if output file is not found.
     */
    public String readFile() throws FileNotFoundException {
        String fileTxt = "";
        File in = new File(outputFilePath);
//        try {
//
//        } catch (FileNotFoundException e) {
//            System.out.println("I/O Exception error");
//            throw new FileNotFoundException();
//        }
        Scanner scan = new Scanner(in);
        while (scan.hasNext()) {
            String currentLine = scan.nextLine();
            fileTxt += currentLine + "\n";
        }
        return fileTxt;
    }

    /**
     * Updates output file based on current TaskList.
     *
     * @param list the TaskList containing all existing Tasks.
     * @throws IOException
     */
    public void updateFile(TaskList list) throws IOException {
        FileWriter out = new FileWriter(outputFilePath);
        String output = updateOutputList(list);
        out.write(output);
        out.close();
    }

    /**
     * Returns a string that will be written to the output file
     *
     * @param list the TaskList containing all existing Tasks.
     * @return text to be written to the output file.
     */
    public static String updateOutputList(TaskList list) {
        String output = "";
        ArrayList<Task> tasks = list.getTasks();
        for (Task task : tasks) {
            if (task != null) {
                output += task.toString() + "\n";
            }
        }
        return output;
    }

}
