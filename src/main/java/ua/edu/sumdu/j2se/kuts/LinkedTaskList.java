package ua.edu.sumdu.j2se.kuts;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList {

    private Node head;
    private Node tail;
    private int amountOfElements = 0;

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

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "head=" + head +
                ", tail=" + tail +
                ", amountOfElements=" + amountOfElements +
                '}';
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




    private class LinkedListIterator implements Iterator<Task> {
        private LinkedTaskList taskList;
        private Task task;
        private Node currentElement;

        public LinkedListIterator(LinkedTaskList taskList) {
            this.taskList = taskList;
            this.task = null;
            this.currentElement = taskList.head;
        }

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public Task next() {
            task = currentElement.data;
            currentElement = currentElement.nextNode;
            return task;
        }

        @Override
        public void remove() {
            if (currentElement == null || task == null) {
                throw new IllegalStateException();
            }
            taskList.remove(task);
        }
    }
    public Iterator<Task> iterator() {
        return new LinkedListIterator(this);
    }
}


