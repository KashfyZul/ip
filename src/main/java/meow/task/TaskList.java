package meow.task;

import java.util.ArrayList;

/**
 * Represents a list of Tasks, stored as an ArrayList.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskListSize;

    /**
     * Constructor for a new TaskList.
     */
    public TaskList() {
        this.tasks =new ArrayList<>();
        this.taskListSize = 0;
    }

    /**
     * @return an ArrayList of all Tasks in the TaskList
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * @return an int representing the number of Tasks in the TaskList
     */
    public int getTaskListSize() {
        return taskListSize;
    }
}
