# Data-Structures-Practice
This repository contains my implementation of some data structure.

###### 1) ArrayList:
- addAtIndex, addToFront, addToBack,get, removeAtIndex, removeFromFront, removeFromBack, isEmpty, size, clear, getBackingArray
      
###### 2) Lazy Delete Linked List:
*When removing a node from the list, the node is marked as deleted, but not actually being removed. When adding a node, if there are no deleted nodes, add the new node normally at the tail of the list. If there are deleted nodes, then we put the data into the deleted node, and set it to undeleted.*

- isEmpty, size, add, compress, clear, contains, remove, deletedNodeCount
      
###### 3) Stack and Queue (implemented using both circular array and linked list)
- Stack: isEmpty, pop, push, size
- Queue: dequeue, enqueue, isEmpty, size

###### 4) Binary Search Tree(without rotations)
*For node removal, inorder successor is used.*
- isEmpty, size, add, max, min, contains, remove, iterator, getPostOrder, getLevelOrder, getPreOrder, getInorder, clear
      
