public class main {
    public static void main (String arg[]) {
        LinkedStack stack  = new LinkedStack ();
        stack.push("a");
        stack.push("b");
        //stack.push("c");
        stack.push("d");
        //stack.pop();
        stack.pop();
        //stack.push("z");
        stack.push(1);
        //stack.push(5);
        //stack.pop();
        //stack.push(5);

        stack.printStack();

        LinkedQueue q = new LinkedQueue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        //q.enqueue(5);
        //q.enqueue(6);
        //q.enqueue(7);
        //q.enqueue(8);

        q.dequeue();
        q.dequeue();
        q.enqueue(6);
        q.dequeue();
        q.enqueue(3);

        q.printQueue();

        ArrayStack s = new ArrayStack();
        System.out.println("\n" + "ArrayStack");
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);
        s.push(7);
        s.push(8);
        s.push(9);
        s.push(10);

        //s.pop();
        //s.pop();
        s.push(11);
        s.pop();

        for (int i = 0; i <= s.getBackingArray().length - 1; i++) {
           System.out.println(s.getBackingArray()[i]);
        }


        ArrayQueue qq = new ArrayQueue();
        System.out.println("\n" + "ArrayQueue");
        qq.enqueue(1);
        qq.enqueue(2);
        qq.enqueue(3);
        qq.enqueue(4);
        qq.enqueue(5);
        qq.enqueue(6);
        qq.enqueue(7);
        qq.enqueue(8);
        qq.enqueue(9);
        qq.enqueue(10);

        qq.dequeue();
        qq.dequeue();
        qq.dequeue();
        qq.enqueue(11);
        qq.enqueue(12);
        //qq.enqueue(13);
        qq.dequeue();
        qq.dequeue();
        qq.enqueue(13);
        //qq.dequeue();
        qq.enqueue(14);

        for (int i = 0; i <= qq.getBackingArray().length - 1; i++) {
            System.out.println(qq.getBackingArray()[i]);
        }
        System.out.println(qq.getFrontEnd());

    }
}
