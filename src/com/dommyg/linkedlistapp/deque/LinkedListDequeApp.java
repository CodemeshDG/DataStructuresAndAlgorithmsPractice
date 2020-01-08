package com.dommyg.linkedlistapp.deque;

/**
 * Allows for practice with linked lists. Practice problems are detailed below.
 * <p>
 * 5.2 Implement a deque based on a doubly linked list. The user should be able to carry out the normal operations on
 * the deque.
 */

public class LinkedListDequeApp {

    public static void main(String[] args) {
        Deque deque = new Deque(8);

        deque.insertAtStart(10);
        deque.insertAtStart(2);

        // PRINT: 2 10 8
        deque.displayQueue();

        deque.insertAtEnd(17);
        deque.insertAtEnd(6);

        // PRINT: 2 10 8 17 6
        deque.displayQueue();

        // PRINT: Removed 6
        System.out.println("Removed " + deque.dequeueFromEnd());

        // PRINT: Removed 17
        System.out.println("Removed " + deque.dequeueFromEnd());

        // PRINT: 2 10 8
        deque.displayQueue();

        // PRINT: Removed 2
        System.out.println("Removed " + deque.dequeueFromStart());

        // PRINT: Removed 10
        System.out.println("Removed " + deque.dequeueFromStart());

        // PRINT: 8
        deque.displayQueue();

        // PRINT: Removed 8
        System.out.println("Removed " + deque.dequeueFromEnd());

        // PRINT: Queue is empty.
        System.out.println(deque.dequeueFromEnd());
    }
}

class Deque {
    private Link first;
    private Link last;
    private ListIterator iterator;

    Deque(long d) {
        first = new Link(d);
        last = first;
        iterator = new ListIterator(this);
    }

    Link getFirst() {
        return first;
    }

    Link getLast() {
        return last;
    }

    /**
     * Inserts a data value into a new link at the start of the queue.
     */
    void insertAtStart(long d) {
        if (isEmpty()) {
            // If queue is empty, make the data the first link.
            first = new Link(d);
            last = first;
        } else {
            Link link = new Link(d);
            first.setPrevious(link);
            link.setNext(first);
            first = link;
        }
    }

    /**
     * Inserts a data value into a new link at the end of the queue.
     */
    void insertAtEnd(long d) {
        if (isEmpty()) {
            // If queue is empty, make the data the first link.
            first = new Link(d);
            last = first;
        } else {
            Link link = new Link(d);
            last.setNext(link);
            link.setPrevious(last);
            last = link;
        }
    }

    /**
     * Removes and returns the next element in the queue from the front.
     */
    long dequeueFromStart() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        Link temp = first;
        if (first == last) {
            // Only one item in the queue. Remove first and last references to it.
            first = null;
            last = null;
        } else {
            // More than one item in the queue. Modify next link to reflect removed data item.
            iterator.resetToStart();
            iterator.nextLink();
            first = iterator.getCurrent();
            first.setPrevious(null);
        }
        return temp.getData();
    }

    /**
     * Removes and returns the next element in the queue from the end.
     */
    long dequeueFromEnd() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        Link temp = last;
        if (last == first) {
            // Only one item in the queue. Remove first and last references to it.
            first = null;
            last = null;
        } else {
            // More than one item in the queue. Modify previous link to reflect removed data item.
            iterator.resetToEnd();
            iterator.previousLink();
            last = iterator.getCurrent();
            last.setNext(null);
        }
        return temp.getData();
    }

    /**
     * Prints out all the data values in the queue from start to end.
     */
    void displayQueue() {
        iterator.resetToStart();
        while (true) {
            if (!iterator.atEnd()) {
                iterator.getCurrent().displayLink();
                iterator.nextLink();
            } else {
                iterator.getCurrent().displayLink();
                System.out.println();
                break;
            }
        }
    }

    private boolean isEmpty() {
        return (first == null);
    }
}

class Link {
    private long data;
    private Link next;
    private Link previous;

    Link(long data) {
        this.data = data;
    }

    long getData() {
        return data;
    }

    Link getNext() {
        return next;
    }

    Link getPrevious() {
        return previous;
    }

    void setNext(Link next) {
        this.next = next;
    }

    void setPrevious(Link previous) {
        this.previous = previous;
    }

    void displayLink() {
        System.out.print(data + " ");
    }
}

class ListIterator {
    private Link current;
    private Link previous;
    private Deque list;

    ListIterator(Deque list) {
        this.list = list;
        resetToStart();
    }

    void resetToStart() {
        current = list.getFirst();
        previous = null;
    }

    void resetToEnd() {
        current = list.getLast();
        previous = current.getPrevious();
    }

    boolean atEnd() {
        return (current.getNext() == null);
    }

    boolean atStart() {
        return (previous == null);
    }

    void nextLink() {
        previous = current;
        current = current.getNext();
    }

    void previousLink() {
        current = previous;
        previous = current.getPrevious();
    }

    Link getCurrent() {
        return current;
    }

    Link getPrevious() {
        return previous;
    }
}
