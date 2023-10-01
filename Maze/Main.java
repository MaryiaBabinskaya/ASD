import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Node {
    // (x, y) koordynaty pola macierzy
    int x, y, dist;

    Node(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

class Main {
    //Nizej arrays pokazuja all przemieszczenia
    private static final int[] row = { -1, 0, 0, 1 };
    private static final int[] col = { 0, -1, 1, 0 };
    //function to prove mozliwosc przemieszczenia na inne pole z danej pozycji
    //false jezeli nie wolno
    private static boolean isValid(char[][] pole, boolean[][] visited, int row, int col){
        return (row >= 0) && (row < pole.length) && (col >= 0) && (col < pole[0].length)
                && pole[row][col] == 'O' && !visited[row][col];
    }
    private static int findShortestPathLength(char[][] pole, int i, int j, int x, int y){
        //if wejscie jest nieprawdziwe
        if (pole == null || pole.length == 'X' || pole[i][j] == 'X' || pole[x][y] == 'X') {
            return -1;
        }
        // macierz `M × N`
        int M = pole.length;
        int N = pole[0].length;
        //macierz to see visited pola
        boolean[][] visited = new boolean[M][N];
        // create queue
        Queue<Node> q = new ArrayDeque<>();
        //begin from (0,0)
        visited[i][j] = true;
        q.add(new Node(i, j, 0));
        int min_dist = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            //delete pierwszy wezel i pracujemy z nim
            Node node = q.poll();
            i = node.x;
            j = node.y;
            int dist = node.dist;
            if (i == x && j == y) {
                min_dist = dist;
                break;
            }

            //check przemieszczenie i wstawiamy kazde do kolejki
            for (int k = 0; k < 4; k++){
                //check czy mozemy przejsc do (i + row[k], j + col[k]) od terazniejszego pola
                if (isValid(pole, visited, i + row[k], j + col[k])){
                    //mark next pole visited and wstawiamy do kolejki
                    visited[i + row[k]][j + col[k]] = true;
                    q.add(new Node(i + row[k], j + col[k], dist + 1));
                }
            }
        }
        if (min_dist != Integer.MAX_VALUE) {
            return min_dist;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char [][] pole = new char [10][10];
        for(int i = 0; i < 10; i++){
            String str = in.nextLine();
            char[] strToArray = str.toCharArray();
            for(int j = 0; j < 10; j++){
                pole[i][j] = strToArray[j];
            }
        }
        in.close();
        int min_dist = findShortestPathLength(pole, 0, 0, 9, 9);

        if (min_dist != -1) {
            System.out.println("Tak");
        } else {
            System.out.println("Nie");
        }
    }
}
//OOOXXOOXXX
//XOXXXXXOOO
//XOOOOOOOXO
//XXXXXXXXXO
//XXXOOOOOOO
//XXXOXXXXOX
//XOOOOOOOOX
//XXXOXOXXOX
//OOOOXOOOOX
//XXXXXXXXOO
//Tak