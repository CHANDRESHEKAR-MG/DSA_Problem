
import java.util.*;

public class GravityGlide {

    static class Slide {
        int x1, y1, x2, y2;
        int slope;

        Slide(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.slope = (y2 - y1) / (x2 - x1);
        }

        boolean contains(int x, int y) {
            return x >= Math.min(x1, x2)
                && x <= Math.max(x1, x2)
                && y == y1 + slope * (x - x1);
        }

        boolean canSlide(int x) {
            if (slope == 1) {
                return x > Math.min(x1, x2);
            }
            return x < Math.max(x1, x2);
        }

        int direction() {
            return slope == 1 ? -1 : 1;
        }
    }

    static int[] gravity(int x, int y, List<Slide> slides) {

        int highest = -1;
        int index = -1;

        for (int i = 0; i < slides.size(); i++) {

            Slide s = slides.get(i);

            if (x < Math.min(s.x1, s.x2) ||
                x > Math.max(s.x1, s.x2)) {
                continue;
            }

            int sy = s.y1 + s.slope * (x - s.x1);

            if (sy < y && sy > highest) {
                highest = sy;
                index = i;
            }
        }

        if (index == -1) {
            return new int[]{x, 0, -1};
        }

        return new int[]{x, highest, index};
    }

    static boolean hasOtherSlide(
        int x, int y, int active, List<Slide> slides
    ) {

        for (int i = 0; i < slides.size(); i++) {
            if (i != active && slides.get(i).contains(x, y)) {
                return true;
            }
        }

        return false;
    }

    static int[] simulate(
        int x, int y, int energy, List<Slide> slides
    ) {

        int active = -1;
        boolean unlocked = false;

        while (true) {

            if (x == 0 || y == 0) {
                return new int[]{x, y};
            }

            if (active == -1) {

                int[] result = gravity(x, y, slides);

                x = result[0];
                y = result[1];
                active = result[2];

                unlocked = false;

                if (y == 0 || x == 0) {
                    return new int[]{x, y};
                }

                continue;
            }

            Slide s = slides.get(active);

            if (!unlocked &&
                hasOtherSlide(x, y, active, slides)) {

                int cost = x * y;

                if (energy < cost) {
                    return new int[]{x, y};
                }

                energy -= cost;
                unlocked = true;
            }

            if (!s.canSlide(x)) {
                active = -1;
                unlocked = false;
                continue;
            }

            if (energy == 0) {
                return new int[]{x, y};
            }

            int dx = s.direction();

            x += dx;
            y -= 1;
            energy--;

            unlocked = false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<Slide> slides = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            slides.add(new Slide(x1, y1, x2, y2));
        }

        int x = sc.nextInt();
        int y = sc.nextInt();
        int energy = sc.nextInt();

        int[] answer = simulate(x, y, energy, slides);

        System.out.println(answer[0] + " " + answer[1]);

        sc.close();
    }
}
