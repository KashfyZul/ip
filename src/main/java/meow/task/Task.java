package meow.task;

/**
 * Represents a Task as desribed by the user.
 */
public class Task {
    private String taskName;
    private char taskType;
    private boolean isDone;
    private int taskNumber;
    private static int taskListSize = 0;

    /**
     * Constructor for a new Task.
     */
    public Task (String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        taskListSize++;
        taskNumber = taskListSize;
    }

    public String getTaskName() {
        return taskName;
    }

    public char getTaskType() {
        return taskType;
    }

    public void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        isDone = true;
    }
    public void setNotDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int num) { taskNumber = num; }

    public static void decrementTaskListSize() { taskListSize--; }

    public static int getTaskListSize() {
        return taskListSize;
    }

    public static void setTaskListSize(int num) {
        taskListSize = num;
    }

    public void printTask() {
        System.out.println("Task is of unknown type");
    }

    public void printTaskAdded() {
        System.out.println("Meowss. Added this task: ");
        printTask();
    }

}
