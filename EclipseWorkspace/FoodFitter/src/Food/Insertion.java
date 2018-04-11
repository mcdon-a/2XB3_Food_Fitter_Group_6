package Food;

/**
* The Insertion class provides methods to perform
* various different types of insertion sort.
*
* @author Robert Sedgewick
* @author Kevin Wayne
* @author Sophia Tao
* @version 1.0
* @since   2018-02-18
*/
public class Insertion {
	
	/**
	 * insertion sort using Comparable. Algorithm referenced from
	 * Robert Sedgewick and Kevin's Wayne's textbook, Algorithms 4th ed.
	 * Retrieved from https://algs4.cs.princeton.edu/21elementary/Insertion.java.html.
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 * @param nutrID - the ID of the nutrient to sort by
	 * @param lo - low inclusive index to sort from
	 * @param hi - high inclusive index to sort to
	 */
	public static void sort( Food[] x, int nutrID, int n, int lo, int hi ) {
		for (int i = lo; i <= hi; i++) {
            for (int j = i; j > 0 && (x[j].getNutr(nutrID)-x[j-1].getNutr(nutrID)) < 0; j--) {
                swap(x, j, j-1);
            }
        }
	}
	
	/**
	 * Swaps two items in an array.
	 * @param x - the input array containing jobs
	 * @param i - an index of an item to be swapped
	 * @param j - an index of an item to be swapped
	 * @return nothing.
	 */
	private static void swap(Food[] x, int i, int j) {
		Food temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}
}