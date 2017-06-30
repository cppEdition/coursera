/**
 * Counting inversions. 
 * An inversion in an array a[] is a pair of entries
 * a[i] and a[j] such that i<j but a[i]>a[j].
 * Given an array, design a linearithmic algorithm
 * to count the number of inversions.
 * */
/*
 *Hint: count while mergesorting. 
 */
/**
 * http://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  Modified above MergeBU code to count the inversions.
 *  ATTENTION ON INV_COUNT increments
 */
public class MergeBU {

	// This class should not be instantiated.
	private MergeBU() { }

	// stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
	private static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

		// copy to aux[]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k]; 
		}
		int inv_count = 0;
		// merge back to a[]
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if      (i > mid)              a[k] = aux[j++];  // this copying is unneccessary
			else if (j > hi)               a[k] = aux[i++];
			else if (less(aux[j], aux[i])) {a[k] = aux[j++]; inv_count +=mid + 1 - i; }
			else                           a[k] = aux[i++];
		}
		return inv_count;
	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 * @param a the array to be sorted
	 */
	public static int sort(Comparable[] a) {
		int n = a.length;
		int inv_count = 0;
		Comparable[] aux = new Comparable[n];
		for (int len = 1; len < n; len *= 2) {
			for (int lo = 0; lo < n-len; lo += len+len) {
				int mid  = lo+len-1;
				int hi = Math.min(lo+len+len-1, n-1);
				inv_count += merge(a, aux, lo, mid, hi);
			}
		}
		assert isSorted(a);
		return inv_count;
	}

	/***********************************************************************
	 *  Helper sorting functions.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}


	/***************************************************************************
	 *  Check if array is sorted - useful for debugging.
	 ***************************************************************************/
	private static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}

	// print array to standard output
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}

	}

	/**
	 * Reads in a sequence of strings from standard input; bottom-up
	 * mergesorts them; and prints them to standard output in ascending order. 
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {



		Integer[] a = {40, 61, 70, 71, 99, 20, 51, 55, 75, 100};

		MergeBU m = new MergeBU();

		int c = m.sort(a);

		System.out.println("After merging:");

		m.show(a);
		System.out.println("---inv count:"+c);

		Integer arr[] ={1, 20, 6, 4, 5};

		int cc =m.sort(arr);

		System.out.println("After merging:");

		m.show(arr);
		System.out.println("---inv count:"+cc);


	}
}
