public class Maman14 {
    public static void main(String[] args) {
        int[] x = {1,2,3,4,5};
        System.out.println(D(x));
    }

    /**
     * Original method from Maman11. The method gets an array and checks the amount of duplicates it has.
     * The given algorithm has the same method names as in the Maman11, that is for the purpose of consistency.
     * @param a - given Array
     * @return the amount of duplicates in a given array.
     */
    public static int D(int[] a){
        int U_Size = 1;
        for(int i = 1; i<a.length; i++){ //
            boolean U = true;d
            for(int j = 0; j<U_Size; j++){
                if(a[i]==a[j]){
                    U = false;
                    j=U_Size;
                }
            }
            if(U){
                U_Size++;
                a[U_Size]=a[i];
            }
        }
        return U_Size;
    }
}
