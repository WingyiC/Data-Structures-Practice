import java.util.Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LazyDeleteLinkedList<T> implements LazyDeleteList<T> {
    Node head, tail;
    int numDelNode, size;
    Stack<Node> deletedNodes;

    public LazyDeleteLinkedList () {
        head = null;
        tail = null;
        size = 0;
        numDelNode = 0;
        deletedNodes = new Stack <Node> ();
    }

    public class Node {
        T data;
        Node prev;
        Node next;
        boolean deleted;

        private Node(T data) {
            this.data = data;
            prev = null;
            next = null;
            deleted = false;
        }
    }

    public class LazyDeleteLinkedListIterator implements Iterator<T> {
        Node curr;
        boolean atStart, atEnd;
        public LazyDeleteLinkedListIterator(Node head) {
            curr = head;
            atStart = true;
        }

        public boolean hasNext() {
            if(curr == null || curr == head && !atStart) {
                return false;
            }
            return true;
        }

        public T next() {
            if (curr == null) {
                throw new NoSuchElementException();
            }
            T data = curr.data;
            atStart = false;
            curr = curr.next;
            return data;
        }
    }

    /**
     * Checks whether this list is empty
     * This should be an O(1) operation
     * @return true if there are no undeleted elements in the list, false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Get the number of undeleted elements in the list
     * This should be an O(1) operation
     * @return the count of undeleted elements
     */
    public int size() {
        return size;
    }

    /**
     * Add a new element to the list.  This element is placed into a deleted node,
     * or if none exists at the end of the list.
     * This should be an O(1) operation for all cases
     * @param data the data element to add to the list
     */
    public void add(T data) {
        Node newNode = new Node(data);
        if (deletedNodes.empty() && isEmpty()) {
            head = newNode;
            tail = newNode;
        }
        if (deletedNodes.empty() && !isEmpty()) {
            newNode.prev = tail;
            newNode.prev.next = newNode;
            tail = newNode;
        }
        if (!deletedNodes.empty()) {
            Node replace = deletedNodes.pop();
            replace.data = newNode.data;
            replace.deleted = false;
            numDelNode--;
        }
        size++;
    }

    /**
     * Remove all the deleted nodes and ensure list contains only undeleted nodes.
     * This is an O(n)
     * @return the number of nodes removed (should count all nodes
     *              that are removed from the list
     */
    public int compress() {
        Node curr = head;
        int numRemoved = 0;
        while (curr != null) {
            if (curr.deleted) {
                if (curr == head && curr == tail) {
                    clear();
                } else if (curr == head) {
                    curr.next.prev = null;
                    head = curr.next;
                } else if (curr == tail) {
                    curr.prev.next = null;
                    tail = curr.prev;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }
                numRemoved++;
            }
            curr = curr.next;
        }
        deletedNodes.clear();
        numDelNode = 0;
        return numRemoved;
    }

    /**
     * Remove everything from the list
     * This is an O(1) operation (you may assume the Stack's clear operation is O(1) )
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Checks whether the list contains a certain value
     * This is an O(n) operation
     * @param data the item to check for
     * @return true if list has this item and it is undeleted, false otherwise
     */
    public boolean contains(T data) {
        Node curr = head;
        while (curr != null) {
            if (!curr.deleted && curr.data.equals(data)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     * Removes an item from the list using lazy deletion (the node is marked deleted, but
     * not actually removed.  If duplicate items are present, this removes the first one found.
     * This is an O(n) operation based on having to find the appropriate item
     *
     * @param data the item being deleted
     * @return true if item was in the list and undeleted, false otherwise.
     */
    public boolean remove(T data) {
      Node curr = head;
      while (curr != null) {
          if (!curr.deleted && curr.data.equals(data)) {
              deletedNodes.push(curr);
              curr.deleted = true;
              numDelNode++;
              size--;
              return true;
          }
          curr = curr.next;
      }
      return false;
    }

    /**
     * This is an O(1) operation (You may assume that Stack.size() is O(1) )
     * @return the number of deleted nodes in the list that are available for use
     */
    public int deletedNodeCount(){
        return numDelNode;
    }

    /**
     * This is an O(n) operation due to the compress function.
     * @return the iterator for this collection.  Asking for an iterator causes a compress of the collection.
     */
    public Iterator<T> iterator() {
        compress();
        return new LazyDeleteLinkedListIterator(head);
    }

    public void printList() {
        Node curr = head;
        System.out.println("Whole List:");
        while(curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println(" ");
        System.out.print("size: " + size);
        System.out.println(" ");
    }

    public void printCompressedList() {
        compress();
        Node curr = head;
        System.out.println("Compressed List:");
        while(curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println(" ");
        System.out.print("size: " + size);
        System.out.println(" ");
    }
}
