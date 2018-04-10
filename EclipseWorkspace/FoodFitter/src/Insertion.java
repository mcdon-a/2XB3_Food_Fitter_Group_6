/**
* The Insertion class provides methods to perform
* various different types of insertion sort.
*
* @author Sophia Tao
* @author Robert Sedgewick
* @author Kevin Wayne
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
	 */
	public static void sortComparable ( Comparable[] x, int n, int lo, int hi ) {
		for (int i = lo; i <= hi; i++) {
            for (int j = i; j > 0 && x[j].compareTo(x[j-1]) < 0; j--) {
                swap(x, j, j-1);
            }
        }
	}
	/**
	 * Lexicographically compares two strings. If the first string
	 * is lexicographically smaller, -1 is returned. If they are
	 * equal, 0 is returned. If it is lexicographically larger,
	 * 1 is returned.
	 * @param x - the first item to be compared
	 * @param y - the second item to be compared
	 * @return an integer describing the result of the comparison.
	 */
	private static int lexCompare(String x, String y) {
		if (x.length() < 1 && y.length() < 1) return 0;
		if (x.length() < 1) return -1;
		if (y.length() < 1) return 1;
		int xPointer = 0;
		int yPointer = 0;
		while (x.charAt(xPointer) == y.charAt(yPointer)) {
			xPointer++;
			yPointer++;
			if (xPointer >= x.length()) {
				if (yPointer >= y.length()) return 0;
				return -1;
			}
			if (yPointer >= y.length()) return 1;
		}
		if (x.charAt(xPointer) > y.charAt(yPointer)) return 1;
		return -1;
	}
	
	/**
	 * Swaps two items in an array.
	 * @param x - the input array containing jobs
	 * @param i - an index of an item to be swapped
	 * @param j - an index of an item to be swapped
	 * @return nothing.
	 */
	private static void swap(Comparable[] x, int i, int j) {
		Comparable temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}
}
