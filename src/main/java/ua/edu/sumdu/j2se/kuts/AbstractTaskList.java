package ua.edu.sumdu.j2se.kuts;

public abstract class AbstractTaskList {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract AbstractTaskList incoming(int from, int to);

    public abstract int size();

}
