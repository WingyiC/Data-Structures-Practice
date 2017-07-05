import java.util.*;

public class BST<E extends Comparable> implements BinaryTree <E> {
    private int count;
    private Bnode root;

    public BST(){
        root = null;
        count = 0;
    }

    private class Bnode<E extends Comparable> {
        public Bnode left, right;
        public E data;

        public Bnode(E data_input) {
            data = data_input;
            left = null;
            right = null;
        }
    }


    private class BSTiterator implements Iterator<E> {
        List<E> list;
        int next_idx, has_idx;

        public BSTiterator(List<E> list_input) {
            list = list_input;
            has_idx = 0;
            next_idx = 0;
        }

        public boolean hasNext() {
            if (list == null) {
                return false;
            }
            if (has_idx >= list.size()) {
                return false;
            }
            has_idx++;
            return true;
        }

        public E next() {
            if (list == null) {
                return null;
            }
            if (next_idx >= list.size()) {
                return null;
            }
            E temp = list.get(next_idx);
            next_idx++;
            return temp;
        }
    }
    /**
     * Adds the item to the tree.  Duplicate items and null items should not be added.
     * Runs in O(log n) expected time, may be linear time in worst case
     *
     * @param item the item to add
     * @return true if item added, false if it was not
     */
    public boolean add(E item) {
        Bnode<E> newNode = new Bnode(item);
        if (contains(item)) {
            return false;
        }
        if (item == null) {
            return false;
        }
        if (root == null) {
            root = newNode;
        } else {
            insert(newNode, root); //compare data of newNode and root
        }
        count++;
        return true;
    }

    private boolean insert(Bnode newNode, Bnode node) {
        if (newNode.data.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = newNode;
            } else {
                return insert(newNode, node.right);
            }
        } else {
            if (node.left == null) {
                node.left = newNode;
            } else {
                return insert(newNode, node.left);
            }
        }
        return true;
    }

    /**
     * returns the maximum element held in the tree.  null if tree is empty.
     * runs in O(log n) expected, may be linear in worst case
     *
     * @return maximum item or null if empty
     */
    public E max() {
        Bnode<E> curr = root;
        if (isEmpty()) {
            return null;
        } else {
            while (curr.right != null) {
                curr = curr.right;
            }
        }
        return curr.data;
    }
    /**
     * returns the number of items in the tree
     * runs in O(1)
     *
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * runs in O(1)
     *
     * @return true if tree has no elements, false if tree has anything in it.
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * runs in O(1)
     * @return the minimum element in the tree or null if empty
     */
    public E min() {
        Bnode<E> curr = root;
        if (isEmpty()) {
           return null;
        } else {
            while (curr.left != null) {
                curr = curr.left;
            }
        }
        return curr.data;
    }

    /**
     * Checks for the given item in the tree.
     * runs in O(log n) expected, may be linear in worst case
     *
     * @param item the item to look for
     * @return true if item is in tree, false otherwise
     */
    public boolean contains(E item) {
        Bnode <E> curr = root;
        while (curr != null) {
            int compare = item.compareTo(curr.data);
            if (compare == 0) {
                return true;
            } else if (compare > 0) {
                curr = curr.right;
            } else if (compare < 0) {
                curr = curr.left;
            }
        }
        return false;
    }

    /**
     * removes the given item from the tree
     * runs in O(log n) expected, may be linear in worst case
     * use in-order successor if necessary
     *
     * @param item the item to remove
     * @return true if item removed, false if item not found
     */
    public boolean remove(E item) {
        if (item == null) {
            return false;
        }
        if (!contains(item)) {
            return false;
        }

        Bnode<E> par = null;
        List<Bnode> parentAndRemove = find(item, root, par);
        Bnode<E> parent = parentAndRemove.get(0); //parent node of the node that needs to be removed
        Bnode<E> remove = parentAndRemove.get(1);  //node that needs to be removed

        if (remove.left == null && remove.right == null) { // if the node has no child
           if (remove == root) {
               root = null;
           } else if (parent.left == remove) {
               parent.left = null;
           } else if (parent.right == remove) {
               parent.right = null;
           }

        } else if (remove.left != null && remove.right != null) { // if the node has two child
            List<Bnode> inOrderSuccessor = findInorderSuccessor(remove.right);

            if (remove.right.left == null) { //if there is no inorder successor, remove's right child is the successor
                remove.right.left = remove.left;
                if (parent != null) { //reset the left/right pointer of remove's parent node
                   if (parent.left == remove) {
                       parent.left = remove.right;
                   } else if (parent.right == remove) {
                       parent.right = remove.right;
                   }
                }
            } else { // if inorder successor exist
                Bnode<E> successor_parent = inOrderSuccessor.get(inOrderSuccessor.size() - 2);
                Bnode<E> successor = inOrderSuccessor.get(inOrderSuccessor.size() - 1);
                remove.data = successor.data; //change the data of remove to its successor's data
                successor_parent.left = null; //remove the successor
            }

        } else if (remove.left != null || remove.right != null) { // if the node has one child
            if (remove.left != null) {
                if (parent.left == remove) {
                    parent.left = remove.left;
                } else if (parent.right == remove) {
                    parent.right = remove.left;
                }
            } else if (remove.right != null) {
                if (parent.right == remove) {
                    parent.right = remove.right;
                } else if (parent.left == remove) {
                    parent.left = remove.right;
                }
            }
        }
        count--;
        return true;
    }

    private List<Bnode> find(E item, Bnode root, Bnode par) { //return the node to removed and its parent
        Bnode<E> curr = root;
        List<Bnode> list = new ArrayList<Bnode>();
        while (curr != null) {
            int compare = item.compareTo(curr.data);
            if (compare == 0) {
                list.add(par); // parent node of the node that needs to be removed
                list.add(curr); //node that needs to be removed
                return list;
            }
            if (compare > 0) {
                par = curr;
                curr = curr.right;
            }
            if (compare < 0) {
                par = curr;
                curr = curr.left;
            }
        }
        return list;
    }

    private List<Bnode> findInorderSuccessor(Bnode remove_right) {
        Bnode<E> successor = remove_right;
        List<Bnode> list = new ArrayList<Bnode>();
        while (successor != null) {
            list.add(successor);
            successor = successor.left;
        }
        return list;
    }

    /**
     * returns an iterator over this collection
     *
     * iterator is based on an in-order traversal
     */
    public Iterator<E> iterator() {
        List<E> list = getInOrder();
        BSTiterator iter = new BSTiterator(list);
        return iter;
    }

    /**
     * Runs in linear time
     * @return a list of the data in post-order traversal order
     */
    public List<E> getPostOrder() {
        List<E> list = new ArrayList<E>();
        if (root == null) {
            return list;
        }
        postOrderTraversal(root, list);
        return list;
    }

    private void postOrderTraversal(Bnode<E> node, List<E> list) { //order: left -> right -> root
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left, list);
        postOrderTraversal(node.right, list);
        list.add(node.data);
    }

    /**
     * Runs in linear time
     * @return a list of the data in level-order traversal order
     */
    public List<E> getLevelOrder() {
        Queue<Bnode> queue = new ArrayDeque<Bnode>();
        List<E> list = new ArrayList<E>();
        Bnode<E> curr = root;

        if (root == null) {
            return list;
        }
        list.add(curr.data);
        while (curr != null) {
            if(curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
            if (queue.isEmpty()) {
                break;
            }
            curr = queue.remove();
            list.add(curr.data);
        }
        return list;
    }

    /**
     * Runs in linear time
     * @return a list of the data in pre-order traversal order
     */
    public List<E> getPreOrder() {
        List<E> list = new ArrayList<E>();
        if (root == null) {
            return list;
        }
        preOrderTraversal(root, list);
        return list;
    }

    private void preOrderTraversal(Bnode<E> node, List<E> list) { //order: root -> left -> right
        if (node == null) {
            return;
        }
        list.add(node.data);
        preOrderTraversal(node.left, list);
        preOrderTraversal(node.right, list);
    }

    /**
     * Runs in linear time
     * @return a list of the data in pre-order traversal order
     */
    public List<E> getInOrder() {
        List<E> list = new ArrayList<E>();
        if (root == null) {
            return list;
        }
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(Bnode<E> node, List<E> list) {  //order: left -> root -> right
        if (node == null) {
            return; 
        }
        inOrderTraversal(node.left, list);
        list.add(node.data);
        inOrderTraversal(node.right, list);
    }

    /**
     * Runs in linear time
     * Removes all the elements from this tree
     */
    public void clear() {
        root = null;
        count = 0;
    }
}
