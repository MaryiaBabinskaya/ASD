public class Fib {
     static void fib(int n, int k1, int k2) {
        if(k2 < 2){
            if(n < 1) System.out.print("0");
            else System.out.print("0 1 1 ");
        }
        if(k1 + k2 < n){
            System.out.print( k1 + k2 + " ");
            fib(n, k2, k1 + k2);
        }
     }
    public static void main(String[] args) {
        int n = 1000;
        fib(n,1,1);
    }
}
//liczba wywolan rekurencyjnych dla n = 1000 -> 15