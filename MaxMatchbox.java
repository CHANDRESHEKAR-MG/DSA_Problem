
import java.util.*;

public class MaxMatchbox {

    static final int SIZE = 250;

    static int n;
    static int[] x, y;
    static boolean isInside(double px, double py) {
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {

            if ((y[i] > py) != (y[j] > py)) {

                double intersect =
                    x[i] + (py - y[i]) *
                    (x[j] - x[i]) / (double) (y[j] - y[i]);

                if (px < intersect) {
                    inside = !inside;
                }
            }
        }

        return inside;
    }

    static double minDistance(double px, double py) {

        double min = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            int j = (i + 1) % n;

            double dx, dy;

            if (x[i] == x[j]) {

                dx = Math.abs(px - x[i]);

                double low = Math.min(y[i], y[j]);
                double high = Math.max(y[i], y[j]);

                dy = Math.max(
                    Math.max(low - py, 0),
                    py - high
                );

            } else {

                // Horizontal edge
                dy = Math.abs(py - y[i]);

                double low = Math.min(x[i], x[j]);
                double high = Math.max(x[i], x[j]);

                dx = Math.max(
                    Math.max(low - px, 0),
                    px - high
                );
            }

            double distance = Math.max(dx, dy);

            min = Math.min(min, distance);
        }

        return min;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        x = new int[n];
        y = new int[n];

        int minEdge = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {

            int j = (i + 1) % n;

            int length = Math.abs(x[i] - x[j])
                       + Math.abs(y[i] - y[j]);

            minEdge = Math.min(minEdge, length);
        }

        
        int maxH = (minEdge * 10 - 1) / 2;

        double[][] distance = new double[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE; j++) {

                double px = (i + 0.5) / 10.0;
                double py = (j + 0.5) / 10.0;

                if (isInside(px, py)) {

                    distance[i][j] =
                        minDistance(px, py) * 10.0;

                } else {

                    distance[i][j] = -1;
                }
            }
        }

        double maxVolume = 0;

        for (int h = 1; h <= maxH; h++) {

            int remainingCells = 0;

            for (int i = 0; i < SIZE; i++) {

                for (int j = 0; j < SIZE; j++) {

                    if (distance[i][j] >= h) {
                        remainingCells++;
                    }
                }
            }
        
            double volume = remainingCells * h / 1000.0;

            maxVolume = Math.max(maxVolume, volume);
        }

        System.out.printf("%.2f%n", maxVolume);

        sc.close();
    }
}
