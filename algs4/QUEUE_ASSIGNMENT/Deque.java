import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private int n;          // size of the stack
    private Node first;     // top of stack
    private Node last;      // bottom of stack
    // helper doubly linked list class
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
        public Node() {
            item = null;
            prev = null;
            next = null;
        }
    }
   // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }                           
   // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }                 
    // return the number of items on the deque
    public int size() {
        return n;
    }                       
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException("Null item can not be inserted");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
       
        if (isEmpty())
            last = first;
        else
            oldFirst.prev = first;
        n++;
        assert check();

    }         
   // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException("Null item can not be inserted");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (isEmpty())
            first = last;
        else 
            oldLast.next = last;
        n++;
        assert check();
    }          
    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item popped = first.item;
        if (n == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        n--;
        assert check();
        return popped;
    }              
    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item popped = last.item;
        if (n == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        n--;
        assert check();
        return popped;
    }               
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();  
    }
   
    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
     // check internal invariants
    private boolean check() {
        // check a few properties of instance variable 'first'
        if (n < 0) {
            StdOut.println("n < 0");
            return false;
        }
        if (n == 0) {
            if (first != null) {
                StdOut.println("n  = 0 and first != null");
                return false;
            }
        }
        else if (n == 1) {
            if (first == null) {
                StdOut.println("n  = 1 and first = null");
                return false;
            }
            if (first.next != null) {
                StdOut.println("n  = 1 and first.next != null");
                return false;
            }
        }
        else {
            if (first == null) {
                StdOut.println("n  > 1 and first = null");
                return false;
            }
            if (first.next == null) { 
                StdOut.println("n  > 1 and first.next = null");
                return false;
            }
        }
        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;
        return true;
    }
}