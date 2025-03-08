package meow.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskListSize;

    public TaskList() {
        this.tasks =new ArrayList<>();
        this.taskListSize = 0;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskListSize() {
        return taskListSize;
    }
}
