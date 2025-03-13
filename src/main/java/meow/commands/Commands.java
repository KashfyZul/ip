package meow.commands;

import meow.task.*;

import java.util.ArrayList;

/**
 * Contains all methods to handle user commands (such as list, delete, mark).
 * Does not handle adding of tasks.
 */
public class Commands {

    public static final String LINE_SEPARATOR = "_____________________________________";

    /**
     * Lists out all available Tasks in list according to their taskNumbers.
     * Formatting of Tasks follows that of the toString() method of each task child class.
     *
     * @param list the TaskList which contains all Tasks.
     */
    public static void listAllTasks(TaskList list) {
        if (Task.getTaskListSize() == 0) {
            System.out.println("This list empty! YEET");
        } else {
            System.out.println("Here are your tasks!");
            ArrayList<Task> tasks = list.getTasks();
            for (Task t : tasks) {
                if (t == null) {
                    break;
                }
                System.out.print(Integer.toString(tasks.indexOf(t) + 1) + ".");
                System.out.println(stringAnyTask(t));
            }
        }
    }

    /**
     * Takes in a String, line (input by the user).
     * Identifies Task specified by the user (in line) from the TaskList, list
     * Changes isDone attribute of the identified class to mark / unmark the task
     * as done / not done.
     *
     * @param line a String containing information about the Task to be marked / unmarked.
     * @param list the TaskList which contains the identified Task.
     */
    public static void markOrUnmark(String line, TaskList list) {
        int taskNumber = Integer.parseInt(line.substring(line.length() - 1));
        ArrayList<Task> tasks = list.getTasks();
        Task taskToMark = tasks.get(taskNumber - 1);
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

    /**
     * Takes in a String, line (input by the user).
     * Identifies Task specified by the user (in line) from the TaskList, list
     * Deletes the identified Task from list
     *
     * @param line a String containing information about the Task to be deleted.
     * @param list the TaskList which contains the identified Task.
     */
    public static void delete(String line, TaskList list) {
        int taskNumber = Integer.parseInt(line.substring(line.length() - 1)) - 1;
        ArrayList<Task> tasks = list.getTasks();
        Task taskToMark = tasks.get(taskNumber);
        System.out.println("Okie, this task has been removed: ");
        System.out.println(stringAnyTask(taskToMark));

        tasks.remove(taskToMark);

        Task.decrementTaskListSize();
        System.out.println(LINE_SEPARATOR);
    }

<<<<<<< Updated upstream
=======
    /**
     * Takes in a String, line (input by the user).
     * Identifies keyword specified by the user (in line).
     * Searches the TaskList, list, for all Tasks containing the keyword.
     * Returns a separate TaskList containing all the matching Tasks.
     *
     * @param line a String containing information about the keyword to be searched.
     * @param list the TaskList which contains all Tasks to search.
     * @return TaskList of all Tasks that match the keyword
     */
    public static TaskList find(String line, TaskList list) {
        String keyword = line.split(" ")[1]; // take second word in line
        // iterate through the entire list of tasks
        ArrayList<Task> tasks = list.getTasks();
        TaskList matchingList = new TaskList();
        ArrayList<Task> matchingTasks = matchingList.getTasks();
        for (Task task : tasks) {
            String taskName = task.getTaskName();
            String[] taskWords = taskName.split(" ");
            for (String word : taskWords) {
                if (word.equals(keyword)) {
                    matchingTasks.add(task);
                    break;
                }
            }
        }
        return matchingList;
    }
>>>>>>> Stashed changes


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

}
