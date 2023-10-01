import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ASD1 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\User\\Desktop\\ASD\\Zadanie1\\src\\INFO.txt"); //wczytywanie pliku
        Scanner in = new Scanner(file);
        PrintWriter zapis = new PrintWriter("C:\\Users\\User\\Desktop\\ASD\\Zadanie1\\src\\Odp.txt"); //zapisywanie

        int n = in.nextInt();
        int m = in.nextInt();
        int[] tab = new int[n + 1]; // np. 0 0 0 0 0 0 0 0 0 0 0 0
        for (int i = 0; i < m; i++) { //zapisanie do tab
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            if (y2 > tab[y1]) { // zapisujemy w krotce najwieksze znaczenie (np moze byc na poczatku stoic 3, a potem przepisze na 2, chociaz priorytet ma 3)
                tab[y1] = y2; // na index y1 stawimy liczbe y2, potem za pomoca tej tab bedziemy patrzyc na poczatek i koniec pretu
            }
        }
        // np. 0 4 0 0 0 6 9 0 9 0 10 0
        int left = 0; //index poczatku
        int right = 0; //index konca
        int max = -1; //max dlugosc
        int counter = 0; //ilosc pasm
        boolean mamy_interwal = false;  //mowi czy teraz znajdujemy sie w interwale
        for (int i = 0; i < n; i++) {
            if (tab[i] == 0) { //jezeli mamy 0
                if (i >= max) { //jezeli nie ma interwalu
                    if (i == max) mamy_interwal = false;
                    right = i + 1; //koniec naszego dobrego kawalku
                }
            } else if (i == tab[i] && !mamy_interwal) { //mamy wzdluz i nie ma interwalu
                if (left < right) {
                    zapis.println("(" + left + " " + right + ")");
                    counter++;
                }
                left = tab[i];
                right = i + 1;
            } else if (i == tab[i] && i == max) { //mamy wzdluz i jestesmy na koncu interwalu
                mamy_interwal = false;
                right = i + 1;
            } else { //jezeli inna liczba niz 0
                if (mamy_interwal) {  //jezeli jestesmy w interwale
                    if (tab[i] > max) {
                        max = tab[i];
                        left = max;
                    }
                    if (i == max) mamy_interwal = false;
                } else { //jezeli nie ma interwalu
                    if (tab[i] > max) max = tab[i];
                    if (right > left) {
                        zapis.println("(" + left + " " + right + ")");
                        counter++;
                    }
                    left = tab[i];
                    mamy_interwal = true;
                }
            }
        }
        if (right > left) {
            zapis.println("(" + left + " " + right + ")");
            counter++;
        }
        zapis.println(counter);
        zapis.close();
    }
}

// Mamy zlozonosc O(m+n), bo mamy 2 petli for(nie zwiazane miedzy soba), jedna idzie po okresie od 0 do m, wtedy robimy przepis z pliku
//do tabelki na ktorej bedziemy pracowac, druga petla idzie po okresie od 0 do n, gdzie znajduje sie sam algorytm
//Oprocz testu jawnego, sprawdzialam swoje

//kiedy w ogole nie mamy miejsca na laser
//5 1
//1 0 1 5

//kiedy nie mamy zadnych m
//5 0

//w tabelkie na poczatku przepisuje 3, a potem porownywa z 2
//5 4
//1 4 1 5
//2 3 2 5
//3 1 3 3
//4 1 4 2

//mamy tylko wzdluz
//4 5
//1 0 1 0
//1 1 1 1
//1 2 1 2
//1 3 1 3
//1 4 1 4

//5 2
//1 3 1 5
//0 0 3 4
//powinno byc pusto

//4 2
//1 3 1 4
//2 0 2 1
//Odp (1 3)