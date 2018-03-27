/**
* A class that implements MergeSort.
* @author Robert Sedgewick
* @author Kevin Wayne
* @author  Sophia Tao
* @version 1.0
* @since   2018-02-19
*/
public class TimSort {
	
	public static void main(String[] args) {
		int M = 200;
		Integer[] toSort1 = {new Integer(2), new Integer(1), new Integer(4)};
		Integer[] toSort2 = new Integer[M];
		for (int i = 0; i < M; i++) {
			toSort2[i] = (int)(100*Math.random());
		}
		sortMerge(toSort2, M);
		for (int i = 0; i < M; i++) {
			System.out.println(toSort2[i]);
		}
	}
	
	static int INSERTION_CUTOFF = 20;
	
	private static Comparable[] aux;
	/**
	 * merge sort using Comparable. Algorithm referenced from
	 * Robert Sedgewick and Kevin's Wayne's textbook, Algorithms 4th ed.
	 * Retrieved from https://algs4.cs.princeton.edu/22mergesort/Merge.java.html
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMerge ( Comparable[] x, int n ) {
		Comparable[] aux = new Comparable[x.length];
        sortMerge(x, aux, 0, n-1);
	}
	
	/**
	 * A recursive method that sorts using comparable. Algorithm partially referenced from
	 * Robert Sedgewick and Kevin's Wayne's textbook, Algorithms 4th ed.
	 * Retrieved from https://algs4.cs.princeton.edu/22mergesort/Merge.java.html
	 * @param x - the first array to be merged
	 * @param y - the second array to be merged
	 * @return the merged array.
	 */
	private static void sortMerge(Comparable[] x, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        if (mid - lo > INSERTION_CUTOFF) sortMerge(x, aux, lo, mid); //added insertion sort
        else Insertion.sortComparable(x, x.length, lo, mid);
        if (mid - lo > INSERTION_CUTOFF) sortMerge(x, aux, mid + 1, hi); //added insertion sort
        else Insertion.sortComparable(x, x.length, mid + 1, hi);
        merge(x, aux, lo, mid, hi);
    }
	
	/**
	 * A method that merges two arrays of type Comparable together
	 * and retains their sorted order. Algorithm referenced from
	 * Robert Sedgewick and Kevin's Wayne's textbook, Algorithms 4th ed.
	 * Retrieved from https://algs4.cs.princeton.edu/22mergesort/Merge.java.html
	 * @param x - the first array to be merged
	 * @param y - the second array to be merged
	 * @return the merged array.
	 */
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
            	a[k] = aux[j++];
            else if (j > hi)
            	a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) <= 0)
            	a[k] = aux[j++];
            else
            	a[k] = aux[i++];
        }
	}
}
