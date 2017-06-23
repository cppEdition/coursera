import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;         // array of items
    private int n;            // number of elements on stack
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        n = 0;
    }
    // is the queue empty?
    public boolean isEmpty() {
        return n == 0;
    }
    // return the number of items on the queue
    public int size() {
        return n;
    }
    // add the item
    public void enqueue(Item item) {
        if (item == null) 
            throw new java.lang.IllegalArgumentException("Null item can not be inserted");
        if (n == items.length) resize(2 * items.length);    // double size of array if necessary
        items[n++] = item;                            // add item
    }           
    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int randomIndex = StdRandom.uniform(0, n);
        Item popped = items[randomIndex];
        items[randomIndex] = items[n - 1];
        items[n - 1] = null;
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == items.length / 4) resize(items.length / 2);
        return popped;
    }                    
    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int index = StdRandom.uniform(n);
        return items[index];
    }                     
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] indices;
        private int current;
        public RandomizedQueueIterator() {
            indices = new int[n];
            current = 0;
            for (int i = 0; i < n; i++)
                indices[i] = i;
            StdRandom.shuffle(indices);
        }
        
        
        public boolean hasNext() {
            return current < n;
        }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = items[indices[current]];
            current++;
            return item;
        }
    }
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;
        
        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }
}