package meow.task;

import java.util.ArrayList;

/**
 * Contains all methods to add tasks to a specified TaskList
 */
public class MakeTasks {

    /**
     * Identifies an Event Task from a string (previously input by user).
     * Adds this Event Task to a specified TaskList
     *
     * @param line a String containing information about the Event to be added
     * @param list the TaskList which the Event is to be added to
     */
    public static void makeNewEvent(String line, TaskList list) {
        Task currTask;
        int taskNameStart = "event ".length();
        int taskNameEnd = line.indexOf('/');
        String taskName = line.substring(taskNameStart, taskNameEnd);
        String from = line.substring(taskNameEnd + "from ".length(), line.lastIndexOf('/') - 1);
        int toStart = line.lastIndexOf('/') + "to ".length();
        String to = line.substring(toStart);
        currTask = new Event(taskName, from, to);
        ArrayList<Task> tasks = list.getTasks();
        tasks.add(currTask);
        currTask.printTaskAdded();
    }

    /**
     * Identifies an Event Task from a string (read from the output txt file).
     * Adds this Event Task to a specified TaskList
     *
     * @param line a String containing information about the Event to be added
     * @param list the TaskList which the Event is to be added to
     */
    public static void makeExistingEvent (String line, TaskList list) {
        Task currTask;
        int taskNameStart = "[E][ ] ".length();
        int taskNameEnd = line.indexOf('(');
        String taskName  = line.substring(taskNameStart, taskNameEnd);
        String from = line.substring(line.indexOf(':') + 1, line.lastIndexOf(':') - 3);
        String to = line.substring(line.lastIndexOf(':') + 1, line.lastIndexOf(')'));
        currTask = new Event(taskName, from, to);
        ArrayList<Task> tasks = list.getTasks();
        tasks.add(currTask);
    }

    /**
     * Identifies a ToDo Task from a string (previously input by user).
     * Adds this ToDo Task to a specified TaskList
     *
     * @param line a String containing information about the ToDo to be added
     * @param list the TaskList which the ToDo is to be added to
     */
    public static void makeNewToDo(String line, TaskList list) {
        Task currTask;
        int taskNameStart = "todo ".length();
        String taskName = line.substring(taskNameStart);
        currTask = new ToDo(taskName);
        ArrayList<Task> tasks = list.getTasks();
        tasks.add(currTask);
        currTask.printTaskAdded();
    }

    /**
     * Identifies a ToDo Task from a string (read from the output txt file).
     * Adds this ToDo Task to a specified TaskList
     *
     * @param line a String containing information about the ToDo to be added
     * @param list the TaskList which the ToDo is to be added to
     */
    public static void makeExistingToDo(String line, TaskList list) {
        Task currTask;
        int taskNameStart = "[T][ ] ".length();
        String taskName  = line.substring(taskNameStart);
        currTask = new ToDo(taskName);
        ArrayList<Task> tasks = list.getTasks();
        tasks.add(currTask);
    }

    /**
     * Identifies a Deadline Task from a string (previously input by user).
     * Adds this Deadline Task to a specified TaskList
     *
     * @param line a String containing information about the Deadline to be added
     * @param list the TaskList which the Deadline is to be added to
     */
    public static void makeNewDeadline(String line, TaskList list) {
        Task currTask;
        int taskNameStart = "deadline ".length();
        int taskNameEnd = line.indexOf('/');
        String taskName = line.substring(taskNameStart, taskNameEnd);
        String by = line.substring(taskNameEnd + "by ".length());
        currTask = new Deadline(taskName, by);
        ArrayList<Task> tasks = list.getTasks();
        tasks.add(currTask);
        currTask.printTaskAdded();
    }

    /**
     * Identifies a Deadline Task from a string (read from the output txt file).
     * Adds this Deadline Task to a specified TaskList
     *
     * @param line a String containing information about the Deadline to be added
     * @param list the TaskList which the Deadline is to be added to
     */
    public static void makeExistingDeadline(String line, TaskList list) {
        Task currTask;
        int taskNameStart = "[D][ ] ".length();
        int taskNameEnd = line.indexOf('(');
        String taskName  = line.substring(taskNameStart, taskNameEnd);
        String by = line.substring(line.indexOf(':') + 1, line.indexOf(')'));
        currTask = new Deadline(taskName, by);
        ArrayList<Task> tasks = list.getTasks();
        tasks.add(currTask);
    }


}
