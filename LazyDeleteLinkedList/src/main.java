public class main {
    public static void main(String[] arg) {
        int numRemoved = 0;
        LazyDeleteLinkedList l = new LazyDeleteLinkedList ();
       /* l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        //l.remove(3);
        l.remove(4);*/

        for (int i=0; i < 20 ; i++){
            l.add("A" + i);
        }

        l.printList();
        System.out.println("Contains: " + l.contains("A6"));


        numRemoved = l.compress();
        l.printCompressedList();
        System.out.println("removed: " + numRemoved);

    }
}
