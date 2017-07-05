public class main {
    public static void main (String[] args) {
        BST t = new BST();
        t.add(2);
        t.add(1);
        /*t.add(5);
        t.add(4);
        t.add(7);
        t.add(6);
        t.add(11);
        t.add(10);
        t.add(9);
        t.add(8);*/

        /*            2
               1             5
                        4            7
                              6              11
                                         10
                                    9
                                8*/
        t.getInOrder();
        t.getPreOrder();
        t.getPostOrder();
        t.getLevelOrder();
        t.remove(1);
        //System.out.println("size: " +t.size());
        System.out.println("Size " + t.getLevelOrder().size());
        for (int i = 0; i < t.size(); i++) {
           //System.out.print(t.getInOrder().get(i) + " ");
           //System.out.print(t.getPreOrder().get(i) + " ");
           //System.out.print(t.getPostOrder().get(i) + " ");
           System.out.print(t.getLevelOrder().get(i) + " ");
        }
    }
}
