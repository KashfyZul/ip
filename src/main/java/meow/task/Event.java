package meow.task;

/**
 * Represents an Event, a task with a start time/day/date (from)
 * and end time/day/date (to).
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Prints this Event according to the specified format.
     */
    @Override
    public void printTask() {
        System.out.println("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName()
                + "(from:" + this.from + " to:"
                + this.to + ")");
    }

    /**
     * Returns a String representing this Event according to the specified format.
     *
     * @return Event as a string in specified format
     */
    public String toString() {
        return ("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.getTaskName()
                + "(from:" + this.from + " to:"
                + this.to + ")");
    }

    /**
     * Constructor for a new Event Task.
     *
     * @param taskName the name of the Event Task.
     * @param from the start time/day/date Task.
     * @param to the end time/day/date Task.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
        this.setTaskType('E');
//        this.printTaskAdded();
    }
}
