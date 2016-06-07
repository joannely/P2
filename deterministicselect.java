
public class deterministicselect {


    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Integer[] a, int lo, int hi, int pivot) {
        int i = lo;
        int j = hi + 1;

        Integer v = a[pivot];
        exch(a, lo, pivot);

        //Integer v = a[lo];
        while (true) { 
            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;
            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;  
            // check if pointers cross
            if (i >= j) break;
            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static void sort(Integer [] a, int lo, int hi) {        
        Boolean isMin = false;
        for(int i = lo; i < hi-lo+1; i++) {
            int min = i;
            isMin = false;
            for(int j = i+1; j < hi-lo+1; j++) {
                if(a[j] < a[min]) {
                    min = j;
                    isMin = true;
                }
            }
            if(isMin == true) {
                int temp = 0;
                temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    public static int choosePivot(Integer[] a, int lo, int hi) {
        int num = hi-lo+1;
        if((hi-lo+1) < 5) {
            return a[lo];
        }
        else if((hi-lo+1) == 5) {
            return a[lo+2];
        }
        int size = (hi-lo+1)/5;
        Integer[] b;

        Boolean overflow;
        if((hi-lo+1) % 5 == 0) {
            overflow = false;
            b = new Integer[size];
        } else {
            overflow = true;         
            b = new Integer[size+1];
        }

        int start = lo;

        for(int i = 0; i < size; i++) {
            sort(a, start, start+4);
            b[i] = a[start+2];
            start = start + 5;
        }

        if(overflow) b[size] = a[start-4];

        return choosePivot(b, 0, b.length-1);

    }

    public static int findPivotLocation(Integer[] a, int pivot) {
        for(int i = 0; i < a.length; i++) {
            if(a[i] == pivot) return i;
        }
        return -1;
    }


    public static Integer select(Integer[] a, int k) {
        int pos = a.length - k;
        if(a.length <= 5) {
            sort(a, 0, 4);
            return a[pos];
        }

        int lo = 0, hi = a.length - 1;




        while(hi > lo) {
            int pVal = choosePivot(a, lo, hi);
            int pIndex = findPivotLocation(a, pVal);
            int i = partition(a, lo, hi, pIndex);
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
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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


        System.out.println(deterministicselect.select(a, k.intValue()));

    }

}