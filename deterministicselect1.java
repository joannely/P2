import java.util.*; 
public class deterministicselect1 {

    private deterministicselect1() { }

    public static Integer Select(Integer[] a, int k, int lo, int hi){
        if(hi - lo == 0){
            //System.out.println("RETURN " + a[hi]);
            return a[hi];
        }
        int n = hi - lo + 1;

        //initialize
        int[] group = new int[5];

        int flag=0;
        if(n%5 != 0){
            flag = 1;
        }

        Integer[] medians = new Integer[n/5 + flag];
        int[] lastgroup = new int[n%5];
 
        int index = lo;

        //split into groups
        for(int i=0; i<n/5; i++){
            for(int j=0; j<5; j++){
                group[j] = a[index];
                index = index + 1;
            }
            Arrays.sort(group);
            /*
            for(int q=0; q<group.length; q++){
                System.out.print(" " + group[q]);
            }
            System.out.println("<< sorted group");
            */
            medians[i] = group[2];

        }//for 

        for(int i=0; i<n%5; i++){
            lastgroup[i] = a[index];
            index = index + 1;
        }

        if(lastgroup.length != 0){
            Arrays.sort(lastgroup);

            /*
            for(int q=0; q<lastgroup.length; q++){
                System.out.print(" " + lastgroup[q]);
            }
            System.out.println(" < sorted lastgroup");
            */
            medians[medians.length-1] = lastgroup[lastgroup.length/2];
        }

        Arrays.sort(medians);

        //find pivot
        int pivot = medians[medians.length/2];
        //System.out.println("pivot: " + pivot);

        Integer[] newarray = new Integer[n];

        int li = 0;
        int ri = n-1;

        for(int i=lo; i<n; i++){
            if(a[i] <= pivot){
                newarray[li] = a[i];
                li = li + 1;
            }
            else{
                newarray[ri] = a[i];
                ri = ri - 1;
            }
        }//for

        if(k <= li){
            //System.out.println("li: " + li);
            return Select(newarray, k, 0, li-1);  
        }

        else{
            //System.out.println("li: " + (li) + " ri: " + (a.length-ri-1));
            return Select(newarray, k, li, a.length-ri-1);
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
        System.out.println(a.length);

        System.out.println(deterministicselect1.Select(a, k, 0, a.length-1));


    }

}