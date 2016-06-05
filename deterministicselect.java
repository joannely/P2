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

import java.util.*; 
public class deterministicselect {

    // This class should not be instantiated.

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.

    public static Integer Select(Integer[] a, int k, int lo, int hi) {
    	if(hi-lo == 0){
    		System.out.println(a[hi]);
    		return a[hi];
    	}

    	int n = hi - lo + 1;
    	int g = 0;
    	if(n % 5 == 0){
    		g = n/5;
    	}
    	else{
    		g = n/5+1;
    	}
    	int index = lo;

    	ArrayList groups = new ArrayList();

    	int tempmed = 0;

    	for(int i=0; i<g; i++){
    		int[] group = new int[5];
    		for(int j=0; j<5; j++){
    			if(index > hi){
    				group[j] = group[0];
    			}
    			group[j] = a[index];
    			index = index + 1;
    		}//littlefor
    		Arrays.sort(group);
    		tempmed = 
    		     
    	}//bigfor

    	int[] medians = new int[g];
    	int[] temparray = new int[5];
    	for(int i=0; i<g; i++){
    		medians[i] = groups.get(i)[2];
    	}

    	Arrays.sort(medians);

    	int p = medians[g/2];

    	int[] newarray = new int[a.length];
    	int li = 0;
    	int ri = a.length-1;
    	for(int i=0; i<a.length; i++){
    		if(a[i] <= p){
    			newarray[li] = a[i];
    			li = li + 1;
    		}
    		else{
    			newarray[ri] = a[i];
    			ri = ri - 1;
    		}
    	}

    	if(li <= k){
    		Select(a, k, 0, li-1);
    	}

    	else{
    		Select(a, k, li, a.length-1);
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


        Select(a, k, 0, a.length-1);

    }

}