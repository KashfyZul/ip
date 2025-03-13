package meow.task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains methods to handle reading from and writing to the output text file
 */
public class Storage {

    static final String outputFilePath = "C:/Users/cyber/Documents/ip/src/main/java/meow/data/meowOutput.txt";

    /**
     * Reads file and returns the text file as a String.
     *
     * @return text from output file as a String.
     * @throws FileNotFoundException if output file is not found.
     */
    public static String readFile() throws FileNotFoundException {
        String fileTxt = "";
        try {
            FileReader in = new FileReader(outputFilePath);
            // read output file char by char
            int currChar;
            while ((currChar = in.read()) != -1) {
                fileTxt = fileTxt + (char)currChar;
            }
            in.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File " + outputFilePath + " is not found");
        } catch (IOException ioe) {
            System.out.println("I/O Exception error");
        }
        return fileTxt;
    }

    /**
     * Updates output file based on current TaskList.
     *
     * @param list the TaskList containing all existing Tasks.
     * @throws IOException
     */
    public static void updateFile(TaskList list) throws IOException {
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
