package meow.commands;

import meow.task.*;

import java.util.ArrayList;

public class Commands {

    public static final String LINE_SEPARATOR = "_____________________________________";

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

    public static void delete(String line, TaskList list) {
        int taskNumber = Integer.parseInt(line.substring(line.length() - 1)) - 1;
        ArrayList<Task> tasks = list.getTasks();
        Task taskToMark = tasks.get(taskNumber);
        System.out.println("Okie, this task has been removed: ");
        System.out.println(stringAnyTask(taskToMark));

        tasks.remove(taskToMark);


//        while (tasks.get(taskNumber) != null) {
//            tasks.get(taskNumber).setTaskNumber(tasks.get(taskNumber).getTaskNumber() - 1);
//            tasks.set(taskNumber, tasks.get(taskNumber + 1));
//            taskNumber++;
//        }
        Task.decrementTaskListSize();
        System.out.println(LINE_SEPARATOR);
    }


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
