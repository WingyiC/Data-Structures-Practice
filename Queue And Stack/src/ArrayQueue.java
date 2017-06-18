import java.util.NoSuchElementException;

public class ArrayQueue<T> implements QueueInterface<T>{

    private T[] backingArray;
    private int size;
    public int front, end;

    public ArrayQueue() {
        backingArray = (T[]) new Object [INITIAL_CAPACITY];
        size = 0;
        front = 0;
        end = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you <b>must not</b>
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    public T dequeue() {
        T data;
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        data = backingArray[front];
        backingArray[front] = null;
        front++;
        size--;
        return data;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary. If a regrow is necessary, you should copy elements to the
     * front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    public void enqueue(T data) {
        if (data.equals(null)) {
            throw new IllegalArgumentException("Data is null.");
        }
        if (size() >= backingArray.length) {
            T[] temp = (T[]) new Object [(int)(INITIAL_CAPACITY * 1.5)];
            int front_idx = front;
                for (int i = 0; i <= backingArray.length - 1; i++) {
                    temp[i] = backingArray[front_idx];
                    front_idx++;
                    if (front_idx >= size()){
                        front_idx = front_idx - size();
                    }
                }
                backingArray = temp;
                front = 0;
        }
            end = (front + size) % (backingArray.length);
            backingArray[end] = data;
            size++;
    }


    public boolean isEmpty() {
       return size == 0;
    }


    public int size() {
        return size;
    }

    public String getFrontEnd() {
        String res = "Front: " + backingArray[front].toString() + " End: " + backingArray[end].toString();
        return res;
    }

    /**
     * Returns the backing array of this stack.
     * @return the backing array
     */
    public Object[] getBackingArray() {
        return backingArray;
    }

}
