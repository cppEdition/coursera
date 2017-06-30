/**
 * Merging with smaller auxiliary array.
 * Suppose that the subarray a[0] to a[n−1] is sorted
 * and the subarray a[n] to a[2∗n−1] is sorted.
 * How can you merge the two subarrays so that
 * a[0] to a[2∗n−1] is sorted using 
 * an auxiliary array of length n (instead of 2n)?
 * */
/*Hint: copy only the left half
 *into the auxiliary array.
*/
/**
 * Hint is explanatory
 * */
public class MergeWithHalfSizeAux<T extends Comparable<T>> {
	public void merge(T[] a, T[] aux, int n) {
		//copy left part of the a to aux
		//(n is mid)
		for (int k = 0; k < n; k++) {
			aux[k] = a[k];
		}
		//i index of aux
		//j index of right part of a
		//k index of merged part of a
		int i = 0; int j = n; int k = 0;
		for (k = 0; k<a.length; k ++)
		{
			if (i >= n) //i exhausted
				a[k] = a[j++];
			else if (j >= a.length) //j exhausted
				a[k] = aux[i++];
			else if (less(aux[i],a[j]))
				a[k] = aux[i++];
			else
				a[k] = a[j++];
		}
	}
	private  boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	// print array to standard output
	public void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	public static void main(String[] args) {

		Integer[] a = {20, 41, 60, 71, 99, 40, 52, 65, 75, 100};
		MergeWithHalfSizeAux<Integer> m = new MergeWithHalfSizeAux<Integer>();
		int N = a.length / 2;
		Integer[] aux = new Integer[N];
		m.merge(a, aux, N);
		System.out.println("After merging:");
		m.show(a);
	}
}
