package ua.edu.sumdu.j2se.Kuts.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private int amountOfTasks;
    int indexTracker = 0;
    private ArrayTaskList subsetOfTasks;
    private Task[] arrOfTasks = new Task[4];

    public ArrayTaskList() {
    }

    public static void main(String[] args) {
        ArrayTaskList taskList = new ArrayTaskList();
        Task task1 = new Task("Dinner with a girl", 1);
        Task task2 = new Task("Morning running", 184);
        Task task3 = new Task("Taking medication", 16);
        Task task4 = new Task("Meeting with friends", 1);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        System.out.println(taskList);
    }

    public void add(Task task) {
        if (indexTracker < arrOfTasks.length) {
            arrOfTasks [indexTracker++] = task;
        }
    }

    public boolean remove(Task task) {
        boolean result = false;

        for(int i = 0; i < size(); i++) {
            Task arrOfTask = arrOfTasks[i];
            if (task.equals(arrOfTask)) {
                arrOfTasks[i] = null;
                result = true;
                indexTracker--;
            }
        }
        return result;
    }

    public int size() {
        return indexTracker;
    }

    Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= size()) {
            throw new IndexOutOfBoundsException("Out of valid range");
        }
        return index < arrOfTasks.length ? arrOfTasks[index] : new Task("", 0);
    }

     ArrayTaskList incoming(int from, int to) {
         ArrayTaskList result = new ArrayTaskList();
         for (Task task : arrOfTasks) {
             if (task.getTime() >= from && task.getTime() <= to) {
                 result.add(task);
             }
         }
        return result;
    }

    public String toString() {
       return "ArrayTaskList {arrOfTasks = " + Arrays.toString(arrOfTasks) + '}';
    }
}
