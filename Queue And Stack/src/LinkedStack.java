import java.util.NoSuchElementException;

public class LinkedStack<T> implements StackInterface<T> {
    private LinkedNode<T> head;
    private int size;

    /**
     * Return true if this stack contains no elements, false otherwise.
     *
     * This method should be implemented in O(n) time.
     *
     * @return true if the stack is empty; false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Pop from the stack.
     *
     * Removes and returns the top-most element on the stack.
     * This method should be implemented in O(1) time.
     *
     * @return the data from the front of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop(){
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T data = head.getData();
        head = head.getNext();
        size--;
        return data;
    }

    /**
     * Push the given data onto the stack.
     *
     * The given element becomes the top-most element of the stack.
     * This method should be implemented in (if array-backed, amortized) O(1)
     * time.
     *
     * @param data the data to add
     * @throws IllegalArgumentException if data is null
     */
    public void push(T data) {
        if (data.equals(null)) {
            throw new IllegalArgumentException("Data is Null");
        }
        if (isEmpty()) {
            head = new LinkedNode<T>(data);
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    /**
     * Return the size of the stack.
     *
     * This method should be implemented in O(1) time.
     *
     * @return number of items in the stack
     */
    public int size(){
        return size;
    }

    /**
     * Returns the head of this stack.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        return head;
    }


    public void printStack() {
        LinkedNode curr = head;
        System.out.print("Stack" + "\n");
        while (curr != null ) {
            System.out.println(curr.getData());
            curr = curr.getNext();
        }
        System.out.print("Stack size: " + size() + "\n");
        System.out.print("Head " + getHead() + "\n\n");

    }

}
// Note: Head is the top of the stack
//       pop(): Remove the head, set head.next as the new head
//       push(): add a new node, set the new node as the head