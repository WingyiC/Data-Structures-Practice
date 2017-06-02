public class ArrayList<T> implements ArrayListInterface<T> {

    private T[] backingArray;
    private int size;

    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data.");
        }
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of bound.");
        }
        //regrow array to twice its original size
        if (size() >= backingArray.length) {
            T[] tempArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i <= backingArray.length - 1; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }
        //shift the array to right
        for (int i = size() - 1; i >= index; i--) {
            backingArray[i + 1] = backingArray[i];
        }
        //insert data into the specified index
        backingArray[index] = data;
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data.");
        }
        //regrow the array to twice of its original size
        if (size() >= backingArray.length) {
            T[] tempArray = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i <= backingArray.length - 1; i++) {
                    tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }
        //shift the array to right
        for (int i = size() - 1; i >= 0; i--) {
            backingArray[i + 1] = backingArray[i];
        }
        //insert data into the front of the array
        backingArray[0] = data;
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null Data.");
        }
        //regrow the array to twice of its original size
        if (size() >= backingArray.length) {
            T[] tempArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i <= backingArray.length - 1; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }
        //insert data into the back of the array
        backingArray[size()] = data;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        T data;
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bound.");
        }
        if (isEmpty()) {
            data = null;
        } else {
            data = get(index);
            backingArray[index] = null;
            //shift the array to left
            for (int i = index + 1; i <= size() - 1; i++) {
                backingArray[i - 1] = backingArray[i];
                //delete shifted element at the back
                if (i == size() - 1) {
                    backingArray[i] = null;
                }
            }
            size--;
        }
        return data;
    }

    @Override
    public T removeFromFront() {
        T data;
        if (isEmpty() ) {
            data = null;
        } else {
            data = get(0);
            backingArray[0] = null;
            //shift the array to left
            for (int i = 1; i <= size() - 1; i++) {
                backingArray[i - 1] = backingArray[i];
                //delete shifted element at front
                if (i == size() - 1) {
                    backingArray[i] = null;
                }
            }
            size--;
        }
        return data;
    }

    @Override
    public T removeFromBack() {
        T data;
        //if the array is empty, return null. Else, remove the last element and return it
        if (isEmpty()) {
            data = null;
        } else {
            data = get(size() - 1);
            backingArray[size() - 1] = null;
            size--;
        }
        return data;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bound.");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        return backingArray;
    }
}
