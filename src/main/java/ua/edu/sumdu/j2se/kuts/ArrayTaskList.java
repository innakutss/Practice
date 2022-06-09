package ua.edu.sumdu.j2se.kuts;

import java.util.*;

public class ArrayTaskList extends AbstractTaskList {

    private int amountOfElements = 0;
    private Task[] arrOfTasks = new Task[5];

    public void add(Task task) {
        if (amountOfElements < arrOfTasks.length) {
            arrOfTasks[amountOfElements++] = task;
        } else {
            arrOfTasks = Arrays.copyOf(arrOfTasks, arrOfTasks.length * 2);
        }
    }

    public boolean remove(Task task) {
        boolean result = false;

        int deleteElementIndex = 0;
        for (int i = 0; i < amountOfElements; i++) {
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
        if ((newSize = amountOfElements - 1) > deleteElementIndex) {
            System.arraycopy(arrOfTasks, deleteElementIndex + 1, arrOfTasks, deleteElementIndex,
                    newSize - deleteElementIndex);
        }
        arrOfTasks[amountOfElements = newSize] = null;
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
        return amountOfElements;

    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= size()) {
            throw new IndexOutOfBoundsException("Out of valid range");
        }
        return index < arrOfTasks.length ? arrOfTasks[index] : new Task("", 0);
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




    private class ArrayTaskListIterator implements Iterator<Task> {
        private int cursor = 0;
        private int lastElement = -1;

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public Task next() {
            int i = cursor;
            if (i >= size()) {
                throw new NoSuchElementException();
            }
            Task nextElement = getTask(i);
            lastElement = i;
            cursor = i + 1;
            return nextElement;
        }
        @Override
        public void remove() {
            if (lastElement < 0)
                throw new IllegalStateException();

            try {
                ArrayTaskList.this.remove(getTask(lastElement));
                cursor--;
                lastElement = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }
    }
        public Iterator<Task> iterator() {
            return new ArrayTaskListIterator();
        }
}
