package meow.task;

/**
 * Represents a Task with a deadline, the deadline can be a time, date, day, etc.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Prints this Deadline according to the specified format.
     */
    @Override
    public void printTask() {
        System.out.println("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName()
                + "(by:" + this.by + ")");
    }

    /**
     * Returns a String representing this Deadline according to the specified format.
     *
     * @return Deadline as a string in specified format
     */
    public String toString() {
        return ("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName()
                + "(by:" + this.by + ")");
    }

    /**
     * Constructor for a new Deadline Task.
     *
     * @param taskName the name of the Deadline Task.
     * @param by the deadline of the Deadline Task.
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        this.setTaskType('D');
//        this.printTaskAdded();
    }
}
