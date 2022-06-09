package ua.edu.sumdu.j2se.kuts;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractTaskList implements Iterable<Task> {

    private int amountOfElements = 0;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    /**
     *
     * @param from period of time that task starts with
     * @param to period of time till what task must be finished
     * @return ArrayTaskList with tasks
     */
    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList abstractList = new ArrayTaskList();
        if (from > to) {
            return abstractList;
        }
        for (int i = 0; i < size(); i++) {
            Task thisTask = getTask(i);
        if (thisTask == null) {
            continue;
        }
        if (thisTask.getTime() > from && thisTask.getTime() <= to && thisTask.isActive()) {
            abstractList.add(thisTask);
        }
        }
        return abstractList;
    }
    public abstract Iterator<Task> iterator();

    @Override
    public String toString() {
        Iterator<Task> iterator = iterator();
        while (!iterator.hasNext())
            return "";
        StringBuilder sb = new StringBuilder();
        int i = 1;
        int size = size();
        for (Task task : this) {
            if (task == null) {
                continue;
            }
            sb.append(task);
            if (size > i) {
                sb.append(" ");
            }
            i++;
        }
        return sb + ";";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTaskList that = (AbstractTaskList) o;
        return amountOfElements == that.amountOfElements;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfElements);
    }
}