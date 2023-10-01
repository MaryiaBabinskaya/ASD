////Zl N^3 (bo mamy 3 petle)
//1 petla wybiera 1 liczbe
//2 wybiera 2 liczbe
//3 wybiera pozostale dwa elementy
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FourSumFast {
    public static int cnt = 0;                                            //ile takich par
    public static List<List<Integer>> FourSum(int[] tab) {
        List<List<Integer>> pary = new ArrayList<>();
        Arrays.sort(tab);
        int n = tab.length;
        for (int i = 0; i < n - 3; i++) { //dla kazdego elementu w array
            //problem 3Sum
            for (int j = i + 1; j < n - 2; j++) {
                //wsk na lewa i prawa strone
                int k = j + 1;
                int l = n - 1;
                //problem 2Sum
                while (k < l) {
                    int currentSum = tab[i] + tab[j] + tab[k] + tab[l];
                    if (currentSum < 0)  k++;
                    else if (currentSum > 0)  l--;
                    else {
                        pary.add(Arrays.asList(tab[i], tab[j], tab[k], tab[l]));
                        cnt++;
                        k++;
                        l--;
                    }
                }
            }
        }
        return pary;
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\User\\Desktop\\ASD\\Zadanie2\\src\\Liczby.txt"); //wczytywanie pliku
        Scanner in = new Scanner(file);
        int N = 6;   //ROZMIAR tab, trzeba zmieniac
        int[] tab = new int [N];
        for (int i = 0; i < N; i++) { //zapisanie do tab
            tab[i] = in.nextInt();
        }
        FourSum(tab);
        System.out.println(cnt);
    }
}



////Zl N^3*logN  (bo mamy 3 for oraz jedno przeszukiwanie binarne)
//// 3 for - bo idziemy po calej tabelce i szukamy co pasuje
//// binary - Należy posortowac tablice i wykonac N(N–1)/2 wyszukiwań binarnych
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class FourSumFast {
//
//    public static int count(int[] a) {
//        Arrays.sort(a);                 //sortujemy tab
//        int N = a.length;
//        int cnt = 0;
//        for (int i = 0; i < N; i++)
//            for (int j = i + 1; j < N; j++)
//                for (int z = j + 1; z < N; z++)
//                    if (BinarySearch.rank(-a[i] - a[j] - a[z], a) > z)
//                        cnt++;
//        return cnt;
//    }
//    public class BinarySearch {
//        public static int indexOf(int[] a, int key) {
//            int lo = 0;
//            int hi = a.length - 1;
//            while (lo <= hi) {
//                // Key is in a[lo..hi] or not present.
//                int mid = lo + (hi - lo) / 2;
//                if (key < a[mid]) hi = mid - 1;
//                else if (key > a[mid]) lo = mid + 1;
//                else return mid;
//            }
//            return -1;
//        }
//        public static int rank(int key, int[] a) {
//            return indexOf(a, key);
//        }
//    }
//    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("C:\\Users\\User\\Desktop\\ASD\\Zadanie2\\src\\Liczby.txt"); //wczytywanie pliku
//        Scanner in = new Scanner(file);
//        int N = 6;   //ROZMIAR tab, trzeba zmieniac
//        int[] tab = new int [N];
//        for (int i = 0; i < N; i++) { //zapisanie do tab
//            tab[i] = in.nextInt();
//        }
//        System.out.println(count(tab));
//    }
//}