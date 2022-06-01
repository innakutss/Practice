package ua.edu.sumdu.j2se.kuts;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList {

    private Node head;
    private Node tail;
    private int amountOfElements = 0;


    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        Node oldTail = tail;
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail = newNode;
            oldTail.setNextNode(newNode);
        }
        amountOfElements++;
    }

    @Override
    public boolean remove(Task task) {
        if (head == null) {
            throw new NoSuchElementException("Element does not exist!");
        }
        Node current = head;
        Node previous = head;
        while (current != null && !task.equals(current.getData())) {
            previous = current;
            current = current.getNextNode();
        }
        if (current != null) {
            if (current == head) {
                head = current.getNextNode();
            } else if (current == tail) {
                tail = previous;
                tail.setNextNode(null);
            } else {
                previous.setNextNode(current.getNextNode());
            }
            previous.setNextNode(current.getNextNode());
            amountOfElements--;
            return true;
        }
        return false;
    }


    public int size() {
        return amountOfElements;
    }

    public Task getTask(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException("Out of valid range");
        }
        int i = 0;
        Node result = head;
        while (i != index) {
            result = result.getNextNode();
            i++;
        }
        return result.getData();
    }

    @Override
    public AbstractTaskList incoming(int from, int to) {
        LinkedTaskList result = new LinkedTaskList();
        Node currentEl = head;
        while (currentEl != null) {
            if (currentEl.data.getTime() > from && currentEl.data.getTime() <= to && currentEl.data.isActive()) {
                result.add(currentEl.data);
            }
            currentEl = currentEl.getNextNode();
        }
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        return amountOfElements == that.amountOfElements;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfElements);
    }


    private class Node {
        private Task data;
        private Node nextNode;

        public Node(Task data) {
            this.data = data;
        }

        public Task getData() {
            return data;
        }

        public void setData(Task data) {
            this.data = data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}

