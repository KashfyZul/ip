package meow.task;

/**
 * Represents a ToDo Task.
 */
public class ToDo extends Task {

    /**
     * Prints this ToDo according to the specified format.
     */
    @Override
    public void printTask() {
        System.out.println("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName());
    }

    /**
     * Returns a String representing this ToDo according to the specified format.
     *
     * @return ToDo as a string in specified format
     */
    public String toString() {
        return ("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName());
    }

    /**
     * Constructor for a new ToDO Task.
     *
     * @param taskName the name of the Event Task.
     */
    public ToDo(String taskName) {
        super(taskName);
        this.setTaskType('T');
//        this.printTaskAdded();
    }
}
