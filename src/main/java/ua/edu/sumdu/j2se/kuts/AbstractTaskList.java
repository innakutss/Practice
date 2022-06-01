package ua.edu.sumdu.j2se.kuts;

public abstract class AbstractTaskList {

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
}
