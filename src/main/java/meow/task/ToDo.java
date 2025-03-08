package meow.task;

public class ToDo extends Task {

    @Override
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

    public ToDo(String taskName) {
        super(taskName);
        this.setTaskType('T');
//        this.printTaskAdded();
    }
}
