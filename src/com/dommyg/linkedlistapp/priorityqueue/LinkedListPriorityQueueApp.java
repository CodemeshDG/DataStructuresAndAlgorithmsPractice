package com.dommyg.linkedlistapp.priorityqueue;

/**
 * Allows for practice with linked lists. Practice problems are detailed below.
 *
 * 5.1 Implement a priority queue based on a sorted linked list. The remove operation on the priority queue should
 * remove the item with the smallest key.
 */

public class LinkedListPriorityQueueApp {

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(6);

        priorityQueue.insert(8);
        priorityQueue.insert(4);
        priorityQueue.insert(12);
        priorityQueue.insert(18);
        priorityQueue.insert(9);

        // PRINT: 4 6 8 9 12 18
        priorityQueue.displayQueue();

        // PRINT: Removed 4
        System.out.println("Removed " + priorityQueue.dequeue());

        // PRINT: 6 8 9 12 18
        priorityQueue.displayQueue();

        // PRINT: Removed 6
        System.out.println("Removed " + priorityQueue.dequeue());
        // PRINT: Removed 8
        System.out.println("Removed " + priorityQueue.dequeue());
        // PRINT Removed 9
        System.out.println("Removed " + priorityQueue.dequeue());
        // PRINT: Removed 12
        System.out.println("Removed " + priorityQueue.dequeue());
        // PRINT: Removed 18
        System.out.println("Removed " + priorityQueue.dequeue());
        // PRINT: Queue is empty.
        System.out.println("Removed " + priorityQueue.dequeue());

        priorityQueue.insert(10);
        priorityQueue.insert(4);
        priorityQueue.insert(108);

        // PRINT: 4 10 108
        priorityQueue.displayQueue();

        // PRINT: Removed 4
        System.out.println("Removed " + priorityQueue.dequeue());
    }
}

/**
 * Creates a priority queue which orders data in ascending order.
 */
class PriorityQueue {
    private Link first;
    private ListIterator iterator;

    PriorityQueue(long d) {
        first = new Link(d);
        iterator = new ListIterator(this);
    }

    Link getFirst() {
        return first;
    }

    /**
     * Inserts a data value into a new link at the appropriate location in the queue.
     */
    void insert(long d) {
        if (isEmpty()) {
            // If queue is empty, make the data the first link.
            first = new Link(d);
        } else {
            // Start at the beginning of queue.
            iterator.reset();
            while (true) {
                if (iterator.getCurrent().getData() < d) {
                    // Data is larger than iterator's current data value.
                    if (iterator.atEnd()) {
                        // Cannot go any further. Must place data at end of queue.
                        insertAfterPoint(d);
                        break;
                    } else {
                        // Go to the next link.
                        iterator.nextLink();
                    }
                } else {
                    // Data is smaller than iterator's current data value.
                    insertAtPoint(d);
                    break;
                }
            }
        }
    }

    /**
     * Inserts data into a link which would be placed before the iterator's current link.
     */
    private void insertAtPoint(long d) {
        Link link = new Link(d);
        link.setNext(iterator.getCurrent());
        if (iterator.atStart()) {
            // At start of queue; set this new link as the first link.
            first = link;
        } else {
            // Inside of queue; set the previous link's next value to this new link.
            iterator.getPrevious().setNext(link);
        }
    }

    /**
     * Inserts data into a link which would be placed after the iterator's current link. Used only if the iterator is at
     * the end of the queue and the data value is larger.
     */
    private void insertAfterPoint(long d) {
        Link link = new Link(d);
        iterator.getCurrent().setNext(link);
    }

    /**
     * Removes and returns the next element in the queue.
     */
    long dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        Link temp = first;
        iterator.reset();
        iterator.nextLink();
        first = iterator.getCurrent();
        return temp.getData();
    }

    /**
     * Prints out all the data values in the queue.
     */
    void displayQueue() {
        iterator.reset();
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

    Link(long data) {
        this.data = data;
    }

    long getData() {
        return data;
    }

    Link getNext() {
        return next;
    }

    void setNext(Link next) {
        this.next = next;
    }

    void displayLink() {
        System.out.print(data + " ");
    }
}

class ListIterator {
    private Link current;
    private Link previous;
    private PriorityQueue list;

    ListIterator(PriorityQueue list) {
        this.list = list;
        reset();
    }

    void reset() {
        current = list.getFirst();
        previous = null;
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

    Link getCurrent() {
        return current;
    }

    Link getPrevious() {
        return previous;
    }
}

