
import java.util.*;

public class SmallestRegion {

    static long minGap(long low, long high, long[][] intervals) {

        List<long[]> blocked = new ArrayList<>();

        for (long[] interval : intervals) {

            long a = Math.min(interval[0], interval[1]) + 1;
            long b = Math.max(interval[0], interval[1]) - 1;

            a = Math.max(a, low);
            b = Math.min(b, high);

            if (a <= b) {
                blocked.add(new long[]{a, b});
            }
        }

        blocked.sort((a, b) -> Long.compare(a[0], b[0]));

        List<long[]> merged = new ArrayList<>();

        for (long[] current : blocked) {

            if (merged.isEmpty() ||
                current[0] > merged.get(merged.size() - 1)[1] + 1) {

                merged.add(current.clone());

            } else {

                long[] last = merged.get(merged.size() - 1);

                last[1] = Math.max(last[1], current[1]);
            }
        }

     
        long minGap = Long.MAX_VALUE;
        long previous = -1;

        long start = low;

        for (long[] interval : merged) {

            long end = interval[0] - 1;

            if (start <= end) {

               
                if (start < end) {
                    return 1;
                }

                if (previous != -1) {
                    minGap = Math.min(minGap, start - previous);
                }

                previous = start;
            }

            start = interval[1] + 1;
        }

      
        if (start <= high) {

            if (start < high) {
                return 1;
            }

            if (previous != -1) {
                minGap = Math.min(minGap, start - previous);
            }
        }

        return minGap;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[][] xIntervals = new long[n][2];
        long[][] yIntervals = new long[n][2];

        for (int i = 0; i < n; i++) {

            long x1 = sc.nextLong();
            long y1 = sc.nextLong();
            long x2 = sc.nextLong();
            long y2 = sc.nextLong();

            xIntervals[i][0] = x1;
            xIntervals[i][1] = x2;

            yIntervals[i][0] = y1;
            yIntervals[i][1] = y2;
        }


        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long x2 = sc.nextLong();
        long y2 = sc.nextLong();

        long minWidth = minGap(
            x1, x2, xIntervals
        );

        long minHeight = minGap(
            y1, y2, yIntervals
        );

       
        System.out.println(minWidth * minHeight);

        sc.close();
    }
}
