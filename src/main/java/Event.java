public class Event extends Task {
    private String from;
    private String to;

    public void printTask() {
        System.out.println("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName()
                + "(from:" + this.from + " to:"
                + this.to + ")");
    }

    public void printTaskAdded() {
        System.out.println("Meowss. Added this task: ");
        printTask();
    }

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
        this.setTaskType('E');
        this.printTaskAdded();
    }
}
