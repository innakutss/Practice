package ua.edu.sumdu.j2se.kuts;

import java.util.Arrays;
import java.util.Objects;

public class ArrayTaskList extends AbstractTaskList {

    private int indexTracker = 0;
    private Task[] arrOfTasks = new Task[5];

    public ArrayTaskList() {
    }

    @Override
    public void add(Task task) {
        if (indexTracker < arrOfTasks.length) {
            arrOfTasks [indexTracker++] = task;
        } else {
            arrOfTasks = Arrays.copyOf(arrOfTasks, arrOfTasks.length * 2);
        }
    }

    @Override
    public boolean remove(Task task) {
        boolean result = false;

        int deleteElementIndex = 0;
        for(int i = 0; i < indexTracker; i++) {
            Task taskFromInternalArray = arrOfTasks[i];
            if (task.equals(taskFromInternalArray)) {
                deleteElementIndex = i;
                result = true;
            }
        }
        if (isNotDeleteElementFound(result)) {
            return false;
        }
        int newSize;
        if ((newSize = indexTracker -1) > deleteElementIndex) {
            System.arraycopy(arrOfTasks, deleteElementIndex + 1, arrOfTasks, deleteElementIndex,
                    newSize - deleteElementIndex);
        }
        arrOfTasks[indexTracker = newSize] = null;
        return result;
    }



    private boolean isNotDeleteElementFound(boolean result) {
        return !result;
    }

    public int getSrcPosition(Task[] arrOfTasks, int i) {
        if (i >= arrOfTasks.length) {
            return arrOfTasks.length - 1;
        }
        return i;
    }

    public int size() {
        return indexTracker;

    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= size()) {
            throw new IndexOutOfBoundsException("Out of valid range");
        }
        return index < arrOfTasks.length ? arrOfTasks[index] : new Task("", 0);
    }

    @Override
    public AbstractTaskList incoming(int from, int to) {
        ArrayTaskList result = new ArrayTaskList();
        for (Task task : arrOfTasks) {
            if (Objects.isNull(task))
                continue;
            if (task.getTime() > from && task.getTime() <= to && task.isActive()) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return Arrays.equals(arrOfTasks, that.arrOfTasks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arrOfTasks);
    }

    public String toString() {
        return "ArrayTaskList {arrOfTasks = " + Arrays.toString(arrOfTasks) + '}';
    }


}