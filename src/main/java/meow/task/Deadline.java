package meow.task;

public class Deadline extends Task {
    private String by;

    public void printTask() {
        System.out.println("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName()
                + "(by:" + this.by + ")");
    }

    public String toString() {
        return ("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName()
                + "(by:" + this.by + ")");
    }

    public void printTaskAdded() {
        System.out.println("Meowss. Added this task: ");
        printTask();
    }

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        this.setTaskType('D');
        this.printTaskAdded();
    }
}
