package meow.task;

public class ToDo extends Task {

    public void printTask() {
        System.out.println("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName());
    }

    public String toString() {
        return ("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName());
    }

    public void printTaskAdded() {
        System.out.println("Meowss. Added this task: ");
        printTask();
    }

    public ToDo(String taskName) {
        super(taskName);
        this.setTaskType('T');
        this.printTaskAdded();
    }
}
