package com.dommyg.queueapp;

/**
 * Allows for practice with queues. Practice problems are detailed below.
 *
 * 4.1 Write a method for the Queue class which displays the contents of the queue. Note that this does not mean simply
 * displaying the contents of the underlying array. You should show the queue contents from the first item inserted to
 * the last, without indicated to the viewer whether the sequence is broken by wrapping around the end of the array. Be
 * careful that one item and no items display properly, no matter where front and rear are.
 *
 * 4.2 Create a Deque class based on the discussion of deques (double-ended queues) in this chapter. It should include
 * insertLeft(), insertRight(), removeLeft(), removeRight(), isEmpty(), and isFull() methods. It will need to support
 * wrap-around at the end of the array, as queues do.
 */
public class QueueApp {

    public static void main(String[] args) {
        Queue queue = new Queue(5);

        queue.insert(10);
        queue.insert(20);
        queue.insert(30);
        queue.insert(40);

        queue.remove();
        queue.remove();
        queue.remove();

        queue.insert(50);
        queue.insert(60);
        queue.insert(70);
        queue.insert(80);

        // PRINT: In queue: 40 50 60 70 80
        queue.display();

        // PRINT: 40 50 60 70 80
        while (!queue.isEmpty()) {
            long n = queue.remove();
            System.out.print(n);
            System.out.print(" ");
        }
        System.out.println();

        // PRINT: There are no items in the queue
        queue.display();

        queue = new Queue(18);

        queue.insert(66);
        queue.insert(45);
        queue.insert(90);
        queue.insert(82);
        queue.insert(74);
        queue.insert(63);
        queue.insert(74);
        queue.insert(78);
        queue.insert(68);
        queue.insert(21);
        queue.insert(55);
        queue.insert(94);
        queue.insert(88);
        queue.insert(74);
        queue.insert(36);
        queue.insert(86);
        queue.insert(71);
        queue.insert(62);

        // PRINT: In queue: 66 45 90 82 74 63 74 78 68 21 55 94 88 74 36 86 71 62
        queue.display();

        Deque deque = new Deque(10);

        deque.insertRight(2);
        deque.insertRight(6);
        deque.insertRight(3);
        deque.insertRight(5);

        // PRINT: In queue (left to right): 2 6 3 5
        deque.display();

        deque.insertLeft(8);
        deque.insertLeft(4);
        deque.insertLeft(7);

        // PRINT: In queue (left to right): 7 4 8 2 6 3 5
        deque.display();

        // PRINT: 7
        System.out.println(deque.removeLeft());

        // PRINT: 4
        System.out.println(deque.removeLeft());

        // PRINT: 5
        System.out.println(deque.removeRight());

        // PRINT: 3
        System.out.println(deque.removeRight());

        // PRINT: 8
        System.out.println(deque.removeLeft());

        // PRINT: 2
        System.out.println(deque.removeLeft());
    }
}

class Queue {
    private int maxSize;
    private long[] queueArray;
    private int front;
    private int rear;
    private int totalItems;

    Queue(int s) {
        maxSize = s;
        queueArray = new long[maxSize];
        front = 0;
        rear = -1;
        totalItems = 0;
    }

    void insert(long j) {
        if (rear == maxSize - 1) {
            // Rear has reached end of array; return to beginning.
            rear = -1;
        }
        queueArray[++rear] = j;
        totalItems++;
    }

    long remove() {
        long temp = queueArray[front++];
        if (front == maxSize) {
            // Front has reached end of array; return to beginning.
            front = 0;
        }
        totalItems--;
        return temp;
    }

    long peekFront() {
        return queueArray[front];
    }

    boolean isEmpty() {
        return (totalItems == 0);
    }

    boolean isFull() {
        return (totalItems == maxSize);
    }

    int size() {
        return totalItems;
    }

    /**
     * Displays all items in the queue without removing them.
     */
    void display() {
        if (isEmpty()) {
            System.out.println("There are no items in the queue");
        } else {
            System.out.print("In queue:");
            for (int i = 0; i < totalItems; i++) {
                System.out.print(" ");
                if (i + front > maxSize - 1) {
                    System.out.print(queueArray[(front + i) - (maxSize)]);
                } else {
                    System.out.print(queueArray[front + i]);
                }
            }
        }
        System.out.println();
    }
}

class Deque {
    private int maxSize;
    private long[] dequeArray;
    private int front;
    private int rear;
    private int totalItems;

    Deque(int s) {
        maxSize = s;
        dequeArray = new long[maxSize];
        front = 0;
        rear = -1;
        totalItems = 0;
    }

    void insertLeft(long j) {
        if (front == 0) {
            // Front is at start of array; must be brought to end of array to add data.
            front = maxSize;
        }
        dequeArray[--front] = j;
        totalItems++;
    }

    void insertRight(long j) {
        if (rear == maxSize - 1) {
            // Front is at end of array; must be brought to start of array to add data.
            rear = -1;
        }
        dequeArray[++rear] = j;
        totalItems++;
    }

    long removeLeft() {
        long temp = dequeArray[front++];
        if (front == maxSize) {
            // Front is at end of array; must be brought to start of array to add data.
            front = 0;
        }
        totalItems--;
        return temp;
    }

    long removeRight() {
        long temp = dequeArray[rear--];
        if (rear == 0) {
            // Rear is at start of array; must be brought to end of array to add data.
            rear = totalItems - 1;
        }
        totalItems--;
        return temp;
    }

    long peekLeft() {
        return dequeArray[front];
    }

    long peekRight() {
        return dequeArray[rear];
    }

    boolean isEmpty() {
        return (totalItems == 0);
    }

    boolean isFull() {
        return (totalItems == maxSize);
    }

    int size() {
        return totalItems;
    }

    /**
     * Displays all items in the deque (from left to right) without removing them.
     */
    void display() {
        if (isEmpty()) {
            System.out.println("There are no items in the queue");
        } else {
            System.out.print("In queue (left to right):");
            for (int i = 0; i < totalItems; i++) {
                System.out.print(" ");
                if (i + front > maxSize - 1) {
                    System.out.print(dequeArray[(front + i) - (maxSize)]);
                } else {
                    System.out.print(dequeArray[front + i]);
                }
            }
        }
        System.out.println();
    }
}