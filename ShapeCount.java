import java.util.*;

public class ShapeCount {

    static class Segment {
        int fixed, start, end;

        Segment(int fixed, int start, int end) {
            this.fixed = fixed;
            this.start = Math.min(start, end);
            this.end = Math.max(start, end);
        }
    }

    static List<Segment> horizontal = new ArrayList<>();
    static List<Segment> vertical = new ArrayList<>();

    
    static boolean covers(List<Segment> segments,
                          int fixed, int from, int to) {

        int current = from;

        while (current < to) {

            int reach = current;

            for (Segment s : segments) {

                if (s.fixed == fixed &&
                    s.start <= current &&
                    s.end > reach) {

                    reach = s.end;
                }
            }

            if (reach == current) {
                return false;
            }

            current = reach;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        TreeSet<Integer> xSet = new TreeSet<>();
        TreeSet<Integer> ySet = new TreeSet<>();

        for (int i = 0; i < n; i++) {

            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            if (y1 == y2) {

                horizontal.add(
                    new Segment(y1, x1, x2)
                );

                ySet.add(y1);

            } else if (x1 == x2) {

                vertical.add(
                    new Segment(x1, y1, y2)
                );

                xSet.add(x1);
            }
        }

        List<Integer> xs = new ArrayList<>(xSet);
        List<Integer> ys = new ArrayList<>(ySet);

        long count = 0;

   
        for (int i = 0; i < ys.size(); i++) {

            for (int j = i + 1; j < ys.size(); j++) {

                int y1 = ys.get(i);
                int y2 = ys.get(j);

                for (int k = 0; k < xs.size(); k++) {

                    for (int l = k + 1; l < xs.size(); l++) {

                        int x1 = xs.get(k);
                        int x2 = xs.get(l);

                        boolean top = covers(horizontal, y1, x1, x2);

                        boolean bottom = covers(horizontal, y2, x1, x2);

                        boolean left = covers(vertical, x1, y1, y2);

                        boolean right = covers(vertical, x2, y1, y2);

                        if (top && bottom && left && right) {
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println(count);

        sc.close();
    }
}