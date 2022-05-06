package ua.edu.sumdu.j2se.Kuts.tasks;

public class Task {

    public static void main(String[] args) {
    }

    /**
     * Constructors Task() create an inactive task that
     * executes at the specified time and have a specified name
     * <p>
     * Method nextTimeAfter() shows the execution time
     * after specified current
     */

    private String title;
    private int time;
    private int startTime;
    private int endTime;
    private int interval;
    private boolean active;
    private boolean repeated;


    Task(String title, int time) {
        this.title = title;
        this.time = time;
    }

    Task(String title, int startTime, int endTime, int interval) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;
    }


    public String getTitle() {
        return title;
    }

    public String setTitle(String title) {
        return this.title = title;
    }

    public boolean isActive(boolean active) {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getRepeatedInterval() {
        return interval;
    }

    public void setTime(int startTime, int endTime, int interval) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;
    }

    public boolean isRepeated(boolean repeated) {
        return repeated;
    }


    public int nextTimeAfter(int current) {
        if (isRepeated(repeated)) {
            if (current < startTime) {
                return startTime;
            } else if (isActive(!active)) {
                return -1;
            }
        }
        return current;
    }
}
