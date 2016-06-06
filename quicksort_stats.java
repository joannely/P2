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
public class quicksort_stats {
    public static int PARTITION_STAGES;
    public static int EXCHANGES;
    public static int COMPARES;

    // This class should not be instantiated.
    private quicksort_stats() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Integer[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Integer[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        PARTITION_STAGES++;
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        assert isSorted(a, lo, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Integer[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        int pivot = lo + (int)(Math.random() * ((hi - lo) + 1));

        Integer v = a[pivot];
        exch(a, lo, pivot);
        EXCHANGES++;

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
                if (j == lo)  {
                    COMPARES++;
                    break;      // redundant since a[lo] acts as sentinel
                }
            }
            COMPARES++;

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
            EXCHANGES++;
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);
        EXCHANGES++;

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * Rearranges the array so that a[k] contains the kth smallest key;
     * a[0] through a[k-1] are less than (or equal to) a[k]; and
     * a[k+1] through a[N-1] are greater than (or equal to) a[k].
     * @param a the array
     * @param k find the kth smallest
     */
    public static Integer select(Integer[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IndexOutOfBoundsException("Selected element out of bounds");
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }



   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Integer v, Integer w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Integer[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Integer[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    private static void insertSort(Integer [] a) {
        Integer temp;
        for (int i = 1; i < a.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(a[j] < a[j-1]){
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                    EXCHANGES++;
                }
                COMPARES++;
            }
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them; 
     * and prints them to standard output in ascending order. 
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {
        PARTITION_STAGES = 0;
        EXCHANGES = 0;
        COMPARES = 0;

        Integer k = new Integer(args[0]);
        int[] b = StdIn.readAllInts();

        Integer[] a = new Integer[b.length];
        for(int i = 0; i < b.length; i++) {
            a[i] = Integer.valueOf(b[i]);
        }

        if(a.length <= k) {
            insertSort(a);
        } else {
           quicksort_stats.sort(a);
        }

        System.out.println("Partitioning Stages: " + PARTITION_STAGES);
        System.out.println("Exchanges: " + EXCHANGES);
        System.out.println("Compares: " + COMPARES);

        // show(a);

    }

}