package ua.edu.sumdu.j2se.kuts;

import java.util.Arrays;

public class LinkedTaskList {

    private Node head;
    private int data;
    private int indexTracker = 0;
    private Task[] taskList = new Task[indexTracker];

    public LinkedTaskList() {
    }
    public void add(Task task) {
        Node newNode = new Node(data);
        if (this.head == null) {
            head = newNode;
            if (indexTracker > taskList.length) {
                taskList[indexTracker++] = task;
            }
        } else {
            Node currentNode = head;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
    }

    public boolean remove(Task task) {
        Node node = head;
        for (int i = 0; i < indexTracker; i++) {
            node = node.getNextNode();
        }
        node.setNextNode(node.getNextNode());
        return false;
    }

    public int size() {
        return indexTracker;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= size()) {
            throw new IndexOutOfBoundsException("Out of valid range");
        }
        return new Task("", 0);
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList result = new LinkedTaskList();
        for (Task task : taskList) {
            if (task.getTime() > from && task.getTime() <= to) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        return Arrays.equals(taskList, that.taskList);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taskList);
    }
}
