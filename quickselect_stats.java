/**
 *  The <tt>Quick</tt> class provides static methods for sorting an
 *  array and selecting the ith smallest element in an array using quicksort.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class quickselect_stats {
    public static int PARTITION_STAGES;
    public static int EXCHANGES;
    public static int COMPARES;
    // This class should not be instantiated.

    private quickselect_stats() { }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Integer[] a, int lo, int hi) {
        PARTITION_STAGES++;

        int i = lo;
        int j = hi + 1;

        int pivot = lo + (int)(Math.random() * ((hi - lo) + 1));

        Integer v = a[pivot];
        exch(a, lo, pivot);

        //Integer v = a[lo];
        while (true) { 
            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) {
                    COMPARES++;
                    break;
                }
            }
            COMPARES++;

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) { 
                    COMPARES++;
                    break;  
                }
            }
            COMPARES++;

            // check if pointers cross
            if (i >= j) break;
            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static Integer findKthLargest(Integer[] a, int k) {
        if (k <= 0 || k > a.length) {
            System.out.println("Index out of bounds");
            System.exit(0);
        }

        int lo = 0, hi = a.length - 1;
        int pos = a.length - k;

        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > pos) hi = i - 1;
            else if (i < pos) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }
    
    // is v < w ?
    private static boolean less(Integer v, Integer w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        EXCHANGES++;
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    // print array to standard output
    private static void show(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }


    /**
     * Reads in a sequence of strings from standard input; quicksorts them; 
     * and prints them to standard output in ascending order. 
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {
        Integer k = new Integer(args[0]);
        int[] b = StdIn.readAllInts();

        Integer[] a = new Integer[b.length];
        for(int i = 0; i < b.length; i++) {
            a[i] = Integer.valueOf(b[i]);
        }


        quickselect_stats.findKthLargest(a, k.intValue());
        System.out.println("Partitioning Stages: " + PARTITION_STAGES);
        System.out.println("Exchanges: " + EXCHANGES);
        System.out.println("Compares: " + COMPARES);
    }

}