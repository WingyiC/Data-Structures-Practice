import java.util.NoSuchElementException;

public class LinkedQueue<T> implements QueueInterface<T>{
    /**
     * The initial capacity of a queue with fixed-size backing storage.
     */
    public static final int INITIAL_CAPACITY = 10;
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /**
     * Dequeue from the front of the queue.
     *
     * This method should be implemented in O(1) time.
     *
     * @return the data from the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        T data;
        if(isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (size() == 1) {
            tail = null;
        }
        data = head.getData();
        head = head.getNext();
        size--;
        return  data;
    }

    /**
     * Add the given data the the queue.
     *
     * This method should be implemented in (if array-backed, amortized) O(1)
     * time.
     *
     * @param data the data to add
     * @throws IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data.equals(null)) {
            throw new IllegalArgumentException("Data is null");
        }
        LinkedNode<T> newNode = new LinkedNode<T> (data);
        if (isEmpty()) {
            head = new LinkedNode<T>(data);
            tail = head;
        } else if (size() == 1) {
           head.setNext(newNode);
           tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * Return true if this queue contains no elements, false otherwise.
     *
     * This method should be implemented in O(1) time.
     *
     * @return true if the queue is empty; false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Return the size of the queue.
     *
     * This method should be implemented in O(1) time.
     *
     * @return number of items in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns the tail of this queue.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        return tail;
    }
    public LinkedNode<T> getHead() {
        return head;
    }

    public void printQueue() {
        LinkedNode curr = head;
        System.out.println("Queue");
        while (curr != null) {
            System.out.println(curr.getData());
            curr = curr.getNext();
        }
        System.out.print("Queue size: " + size() + "\n");
        System.out.print("Head " + getHead() + "\n");
        System.out.print("Tail " + getTail() + "\n");
    }
}

//Note: head is the front of the queue(first entered), tail is the end of the queue(last entered)
//      dequeue(): remove the head, set head.next as the new head
//      enqueue(): add a new Node, set tail.next as the new node, set the newly added node as the tail
