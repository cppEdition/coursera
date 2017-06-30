import java.util.Iterator;
import java.util.NoSuchElementException;

/*QUESTION:Shuffling a linked list.
 *Given a singly-linked list containing n items,
 *rearrange the items uniformly at random.
 *Your algorithm should consume a logarithmic (or constant) amount of extra memory
 *and run in time proportional to nlogn in the worst case. 
 * */

/*HINT:
 *design a linear-time subroutine 
 *that can take two uniformly shuffled linked lists 
 *of sizes n1 and n2 and 
 *combined them into a uniformly shuffled linked lists
 * of size n1+n2. 
 * */

/*From :
 * https://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time
 * Perform the same procedure as merge sort. 
 * When merging, instead of selecting an element 
 * (one-by-one) from the two lists in sorted order, 
 * flip a coin. Choose whether to pick an element
 * from the first or from the second list based on
 * the result of the coin flip.
 * 
 * https://www.quora.com/What-is-the-most-efficient-way-to-randomize-shuffle-a-linked-list
 */
import edu.princeton.cs.algs4.StdRandom;
public class ShuffleSLList <E>  {
	private Node head;
	private Node headA;
	private Node headB;
	private int sza =0;
	private int szb=0;
	public ShuffleSLList(Node head){
		this.head=head;
		headA=null;
		headB=null;
		sza =0;
		szb=0;
	}
	// helper linked list class
	private class Node {
		private E item;
		private Node next;
		public Node(E x) {
			this.item = x;
			this.next = null;
		}
	}

	private Node merge(Node h1, Node h2) {
		Node left = h1;
		Node right = h2;

		if (StdRandom.uniform(1) > 0) {
			h1 = right;
			right = right.next;
		}
		else {
			left = left.next;
		}

		Node runner = h1;

		while (right != null || left != null) {
			if (left == null) {
				runner.next = right;
				right =right.next;
			}
			else if (right == null) {
				runner.next = left;
				left = left.next;
			}
			else if (StdRandom.uniform(1) > 0) {
				runner.next = right;
				right = right.next;
			}
			else {
				runner.next = left;
				left = left.next;
			}
			runner = runner.next;
		}
		return runner;
	}

	public void shuffle(Node head, int N) {
		if (N == 1) return;

		int k = 1;
		Node mid = head;
		while (k < N / 2) {
			mid = mid.next;
			k++;
		}
		Node h2 = mid.next;
		mid.next = null;
		shuffle(head, N / 2);
		shuffle(h2, N - N / 2);
		merge(head, h2);
	}


	/**
	 * Split the linked list into two
	 * @param head
	 * @return 
	 */
	public void split(Node head) {
		Node cur = head;
		Node curA= null;
		Node curB = null;
		while (cur!=null) {
			curA.item = cur.item;
			curA.next = null;
			if (headA == null) {
				headA = curA;

			}
			sza++;
			cur = cur.next;
			if(cur!=null) {
				curB.item = cur.item;
				curB.next = null;
				if (headB == null) {
					headB = curB;
				}
				cur = cur.next;
				szb++;
			}
		}
	}

}
