public class Task {
    private String taskName;
    private char taskType;
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

    public char getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        char type = ' ';
        if (taskType.equals("todo")) {
            type = 'T';
        }
        else if (taskType.equals("deadline")) {
            type = 'D';
        }
        else if (taskType.equals("event")) {
            type = 'E';
        }
        this.taskType = type;
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

    public static int getTaskListSize() {
        return taskListSize;
    }
}
