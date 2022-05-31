package course.java.homeWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainHomeWork03 {
    static List<int[]> start(char[][] board) {

        int rows = board.length;
        int columns = board[0].length;

        final int currTrip = -1;
        List<int[]> myArr = new ArrayList<>();


        int[][] arrMatrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (arrMatrix[i][j] != 0) {
                    continue;
                }
                List<int[]> points = new ArrayList<>();

                int row = i;
                int col = j;
                arrMatrix[i][j] = currTrip;
                myFilters(board, rows, columns, currTrip, arrMatrix, points, row, col);

            }
        }

        int[] ans = getResult(rows, columns, arrMatrix);

        myArr.add(ans);
        return myArr;
    }

    private static int[] getResult(int rows, int columns, int[][] arrMatrix) {
        int[] ans = new int[3];
        int maxVisits = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (arrMatrix[i][j] > maxVisits) {
                    maxVisits = arrMatrix[i][j];
                    ans[0] = i + 1;
                    ans[1] = j + 1;
                    ans[2] = maxVisits;
                }
            }
        }
        return ans;
    }

    private static void myFilters(char[][] board, int rows, int columns, int currTrip, int[][] arrMatrix, List<int[]> points, int row, int col) {
        while (true) {
            points.add(new int[]{row, col});

            char character = board[row][col];
            if (character == 'L') {
                col--;
            } else if (character == 'R') {
                col++;
            } else if (character == 'U') {
                row--;
            } else if (character == 'D') {
                row++;
            }
            if (row < 0 || row >= rows || col < 0 || col >= columns) {
                // Извън матрица

                Collections.reverse(points);
                int d = 1;
                for (int[] point : points) {
                    arrMatrix[point[0]][point[1]] = d;
                    d++;
                }
                break;
            } else if (arrMatrix[row][col] == currTrip) {
                // detected loop to self
                Collections.reverse(points);
                int k = 0;
                while (points.get(k)[0] != row || points.get(k)[1] != col) {
                    k++;
                }

                for (int h = 0; h < points.size(); h++) {
                    int[] point = points.get(h);
                    if (h <= k) arrMatrix[point[0]][point[1]] = k + 1;
                    else arrMatrix[point[0]][point[1]] = h + 1;
                }
                break;
            } else if (arrMatrix[row][col] > 0) {

                Collections.reverse(points);
                int d = arrMatrix[row][col] + 1;
                for (int[] point : points) {
                    arrMatrix[point[0]][point[1]] = d;
                    d++;
                }
                break;
            }
            arrMatrix[row][col] = currTrip;


        }
    }


    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(System.in);
        String nameOfFile = file.nextLine();
        Scanner scanner = new Scanner(new File(nameOfFile));

        int numTimes = scanner.nextInt();

        for (int t = 1; t <= numTimes; t++) {

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            char[][] board = new char[row][col];
            for (int i = 0; i < row; i++) {
                String s = scanner.next();
                for (int j = 0; j < col; j++) {


                    board[i][j] = s.charAt(j);
                }

            }

            List<int[]> result = start(board);


            for (int[] elements : result) {
                System.out.printf("%d %d %d;%n", elements[0], elements[1], elements[2]);


            }
            scanner.nextLine();
        }

    }

}

