package meow.task;

import java.util.ArrayList;

public class MakeTasks {

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

    public static void makeNewToDo(String line, TaskList list) {
        Task currTask;
        int taskNameStart = "todo ".length();
        String taskName = line.substring(taskNameStart);
        currTask = new ToDo(taskName);
        ArrayList<Task> tasks = list.getTasks();
        tasks.add(currTask);
        currTask.printTaskAdded();
    }

    public static void makeExistingToDo(String line, TaskList list) {
        Task currTask;
        int taskNameStart = "[T][ ] ".length();
        String taskName  = line.substring(taskNameStart);
        currTask = new ToDo(taskName);
        ArrayList<Task> tasks = list.getTasks();
        tasks.add(currTask);
    }

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
