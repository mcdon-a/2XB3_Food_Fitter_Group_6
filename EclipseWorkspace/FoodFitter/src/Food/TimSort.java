package Food;

/**
* A class that implements TimSort.
* @author Robert Sedgewick
* @author Kevin Wayne
* @author  Sophia Tao
* @version 1.0
* @since   2018-02-19
*/
public class TimSort {
	

	static int INSERTION_CUTOFF = 20;
	
	private static Comparable[] aux;
	/**
	 * merge sort using Comparable. Algorithm referenced from
	 * Robert Sedgewick and Kevin's Wayne's textbook, Algorithms 4th ed.
	 * Retrieved from https://algs4.cs.princeton.edu/22mergesort/Merge.java.html
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMerge ( Food[] x, int nutrID) {
		Food[] aux = new Food[x.length];
        sortMerge(x, aux, nutrID, 0, x.length-1);
	}
	
	/**
	 * A recursive method that sorts using comparable. Algorithm partially referenced from
	 * Robert Sedgewick and Kevin's Wayne's textbook, Algorithms 4th ed.
	 * Retrieved from https://algs4.cs.princeton.edu/22mergesort/Merge.java.html
	 * @param x - the first array to be merged
	 * @param y - the second array to be merged
	 * @param lo - the lower end of the array
	 * @param hi - the higher end of the array
	 * @param nutrID - the ID of the nutrient value being sorted
	 * @return the merged array.
	 */
	private static void sortMerge(Food[] x, Food[] aux, int nutrID, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        if (mid - lo > INSERTION_CUTOFF) sortMerge(x, aux, nutrID, lo, mid); //added insertion sort
        else Insertion.sort(x, nutrID, x.length, lo, mid);
        if (mid - lo > INSERTION_CUTOFF) sortMerge(x, aux, nutrID, mid + 1, hi); //added insertion sort
        else Insertion.sort(x, nutrID, x.length, mid + 1, hi);
        merge(x, aux, nutrID, lo, mid, hi);
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
	private static void merge(Food[] a, Food[] aux, int nutrID, int lo, int mid, int hi) {
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
            else if ((aux[j].getNutr(nutrID)-aux[i].getNutr(nutrID)) <= 0)
            	a[k] = aux[j++];
            else
            	a[k] = aux[i++];
        }
	}
}