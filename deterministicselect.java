import java.util.*; 
public class deterministicselect {

    // This class should not be instantiated.

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.

    public static Integer Select(Integer[] a, int k, int lo, int hi){
    	if(hi-lo == 0){
    		System.out.println(a[hi]);
    		return a[hi];
    	}
    	System.out.println("lo: "+lo+" hi: "+hi);

    	int n = hi - lo + 1;
    	int g = n/5;
    	int index = lo;
    	int bit = 0;

    	if(n%5 != 0){
    		bit = 1;
    	}

    	int[] medians = new int[n/5 + bit];

    	

    	int[] group = new int[5];
    	int[] lastgroup = new int[n%5];
   
    	System.out.println("n "+n);
    	System.out.println("g "+g);
    	System.out.println("lastgroupsize: " + lastgroup.length);

    	for(int i=0; i<g; i++){
    	 	System.out.println("here" + index);
    	 	for(int j=0; j<5; j++){
    	 		group[j] = a[index];
    			index = index + 1;
    		}
    		Arrays.sort(group);
    		for(int q=0; q<5; q++){
    			System.out.print(group[q]+" ");
    		}
    		System.out.print("\n");
    		medians[i] = group[2];
	
    	 }//for
    		

    	for(int i=0; i<lastgroup.length; i++){
    		lastgroup[i] = a[index+i];
    	}


    	Arrays.sort(lastgroup);
    	if(lastgroup.length != 0){
    		medians[medians.length-1] = lastgroup[lastgroup.length/2];
    	}
    	
    	Arrays.sort(medians);

    	int p = medians[g/2];

    	System.out.println("pivot: " + p);

    	Integer[] newarray = new Integer[a.length];
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
    	for(int q=0; q<newarray.length; q++){
    		System.out.print(newarray[q] + " ");
    	}
    	System.out.print("\n");
    	System.out.println("li "+li);
    	System.out.println("ri "+(ri-1));

    	if(li <= k){
    		return Select(newarray, k, 0, li-1);
    	}

    	else{
    		return Select(newarray, k, li, a.length-1);
    	}

    }
    
    /**
     * Reads in a sequence of strings from standard input; quicksorts them; 
     * and prints them to standard output in ascending order. 
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {

<<<<<<< HEAD
=======
    	// System.out.println("hi");
>>>>>>> 6566049a8be22b32ac311066f45340bacc973eef
        Integer k = new Integer(args[0]);
        int[] b = StdIn.readAllInts();

        Integer[] a = new Integer[b.length];
        for(int i = 0; i < b.length; i++) {
            a[i] = Integer.valueOf(b[i]);
        }
        System.out.println(a.length);


        Select(a, k, 0, a.length-1);

    }

}