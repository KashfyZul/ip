public class Task {
    private String taskName;
    private boolean isDone;
    private int taskNumber;
    private static int taskListSize = 0;

    public Task (String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        taskListSize++;
        taskNumber = taskListSize;
    }

    public String getTaskName() {
        return taskName;
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

    public int getTaskNumber() {
        return taskNumber;
    }

    public static int getTaskListSize() {
        return taskListSize;
    }
}
