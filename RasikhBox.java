
import java.util.*;

public class RasikhBox {
    static void applyGravity(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        for (int col = 0; col < n; col++) {
            int write = m - 1;

            for (int row = m - 1; row >= 0; row--) {
                if (box[row][col] == '*') {
                    box[row][col] = '.';
                    box[write][col] = '*';
                    write--;
                }
            }
        }
    }
    static char[][] rotateRight(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] rotated = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][m - 1 - i] = box[i][j];
            }
        }

        return rotated;
    }
    static char[][] rotateLeft(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] rotated = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[n - 1 - j][i] = box[i][j];
            }
        }

        return rotated;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        char[][] box = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                box[i][j] = sc.next().charAt(0);
            }
        }
        applyGravity(box);

        int k = sc.nextInt();

        for (int i = 0; i < k; i++) {
            String direction = sc.next();

            if (direction.equalsIgnoreCase("right")) {
                box = rotateRight(box);
            } else if (direction.equalsIgnoreCase("left")) {
                box = rotateLeft(box);
            }

            applyGravity(box);
        }
        for (char[] row : box) {
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j]);

                if (j < row.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        sc.close();
    }
}
