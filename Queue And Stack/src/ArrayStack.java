import java.util.NoSuchElementException;

public class ArrayStack<T> implements StackInterface<T>{

    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }


    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    public T pop() {
        T data;
        if (isEmpty()) {
            throw  new NoSuchElementException("Stack is empty.");
        }
        data = backingArray[size() - 1];
        backingArray[size() - 1 ] = null;
        size--;
        return data;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary.
     *
     * @see StackInterface# push()
     */
    public void push(T data) {
        if (data.equals(null)) {
            throw new IllegalArgumentException("Data is null");
        }
        if (size() >= backingArray.length) {
            T[] temp = (T[]) new Object [(int)(INITIAL_CAPACITY * 1.5)];
            for (int i = 0; i <= backingArray.length - 1; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size()] = data;
        size++;
    }


    public int size() {
        return size;
    }

    /**
     * Returns the backing array of this stack.
     * @return the backing array
     */
    public Object[] getBackingArray() {
        return backingArray;
    }
}
