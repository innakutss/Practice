package ua.edu.sumdu.j2se.Kuts;

import java.util.Objects;

public class Task {

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


    Task(String title, int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("Negative number");
        }
        this.title = title;
        this.time = time;
        this.active = false;
    }

    Task(String title, int startTime, int endTime, int interval) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;
        this.active = false;
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

    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }

    public int getTime() {
        if (repeated) {
            return startTime;
        }
        return time;
    }

    public void setTime(int time) {
        if (repeated) {
            repeated = false;
        }
        this.time = time;
    }

    public int getStartTime() {
        if (!repeated) {
            return time;
        }
        return startTime;
    }

    public int getEndTime() {
        if (!repeated) {
            return time;
        }
        return endTime;
    }

    public int getRepeatedInterval() {
        if (!repeated) {
            return 0;
        }
        return interval;
    }

    public void setTime(int startTime, int endTime, int interval) {
        if (!repeated) {
            repeated = true;
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;

    }

    public boolean isRepeated() {
        return repeated;
    }

    @Override
    public String toString() {
        return "Task {" +
                "title = '" + title + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) o;
        return title.equals(otherTask.title) && time == otherTask.time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time);
    }

    /**
     * nextTimeAfter() method returns time of the next task execution after specified time current.
     * if after specified time current task is not executed, method returns -1.
     */

    public int nextTimeAfter(int current) {
        if (!repeated || current >= endTime) {
            return -1;
        }
        int curr = startTime;
        while (curr < current) {
            curr += interval;
        }
        return curr;
    }
}

