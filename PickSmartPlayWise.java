
import java.util.*;

public class PickSmartPlayWise {

    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Stick {
        Point a, b;
        List<Integer> points = new ArrayList<>();

        Stick(Point a, Point b) {
            this.a = a;
            this.b = b;
        }
    }

    static class Edge {
        int to, id;
        double weight;

        Edge(int to, int id, double weight) {
            this.to = to;
            this.id = id;
            this.weight = weight;
        }
    }

    static List<Stick> sticks = new ArrayList<>();
    static List<Point> nodes = new ArrayList<>();
    static List<Double> weights = new ArrayList<>();
    static List<List<Edge>> graph = new ArrayList<>();

    static Map<String, Integer> nodeMap = new HashMap<>();

    static boolean[] visited;
    static int[] parent, parentEdge, depth;

    static double polygonArea;
    static double polygonPerimeter;
    static boolean found;

    static double cross(double ax, double ay, double bx, double by) {
        return ax * by - ay * bx;
    }

    static String key(double x, double y) {
        return String.format(Locale.US, "%.2f,%.2f", x, y);
    }

    static int getNode(double x, double y) {
        x = Math.round(x * 100.0) / 100.0;
        y = Math.round(y * 100.0) / 100.0;

        String k = key(x, y);

        if (nodeMap.containsKey(k)) {
            return nodeMap.get(k);
        }

        int id = nodes.size();

        nodes.add(new Point(x, y));
        graph.add(new ArrayList<>());

        nodeMap.put(k, id);

        return id;
    }

    static void addIntersection(Stick s1, Stick s2) {

        double x1 = s1.a.x, y1 = s1.a.y;
        double x2 = s1.b.x, y2 = s1.b.y;

        double x3 = s2.a.x, y3 = s2.a.y;
        double x4 = s2.b.x, y4 = s2.b.y;

        double rx = x2 - x1;
        double ry = y2 - y1;

        double sx = x4 - x3;
        double sy = y4 - y3;

        double den = cross(rx, ry, sx, sy);

        if (Math.abs(den) < 1e-9) {
            return;
        }

        double qx = x3 - x1;
        double qy = y3 - y1;

        double t = cross(qx, qy, sx, sy) / den;
        double u = cross(qx, qy, rx, ry) / den;

        if (t >= -1e-9 && t <= 1 + 1e-9 &&
            u >= -1e-9 && u <= 1 + 1e-9) {

            double ix = x1 + t * rx;
            double iy = y1 + t * ry;

            int id = getNode(ix, iy);

            s1.points.add(id);
            s2.points.add(id);
        }
    }

    static void buildGraph() {

        for (Stick s : sticks) {

            s.points.add(getNode(s.a.x, s.a.y));
            s.points.add(getNode(s.b.x, s.b.y));
        }

        for (int i = 0; i < sticks.size(); i++) {
            for (int j = i + 1; j < sticks.size(); j++) {
                addIntersection(sticks.get(i), sticks.get(j));
            }
        }

        for (Stick s : sticks) {

            double dx = s.b.x - s.a.x;
            double dy = s.b.y - s.a.y;

            double len2 = dx * dx + dy * dy;

            s.points.sort((p1, p2) -> {

                Point a = nodes.get(p1);
                Point b = nodes.get(p2);

                double t1 = ((a.x - s.a.x) * dx +
                             (a.y - s.a.y) * dy) / len2;

                double t2 = ((b.x - s.a.x) * dx +
                             (b.y - s.a.y) * dy) / len2;

                return Double.compare(t1, t2);
            });

            List<Integer> unique = new ArrayList<>();

            for (int id : s.points) {
                if (unique.isEmpty() ||
                    unique.get(unique.size() - 1) != id) {
                    unique.add(id);
                }
            }

            for (int i = 0; i + 1 < unique.size(); i++) {

                int u = unique.get(i);
                int v = unique.get(i + 1);

                if (u == v) continue;

                Point a = nodes.get(u);
                Point b = nodes.get(v);

                double w = Math.hypot(a.x - b.x, a.y - b.y);

                int eid = weights.size();

                weights.add(w);

                graph.get(u).add(new Edge(v, eid, w));
                graph.get(v).add(new Edge(u, eid, w));
            }
        }
    }

    static void calculateCycle(int u, int v, int backEdge) {

        List<Integer> path = new ArrayList<>();

        int cur = u;

        while (cur != v) {
            path.add(cur);
            cur = parent[cur];
        }

        path.add(v);
        Collections.reverse(path);

        double area = 0;
        double perimeter = weights.get(backEdge);

        for (int i = 0; i < path.size(); i++) {

            Point a = nodes.get(path.get(i));
            Point b = nodes.get(path.get((i + 1) % path.size()));

            area += a.x * b.y - b.x * a.y;

            if (i < path.size() - 1) {
                perimeter += weights.get(parentEdge[path.get(i + 1)]);
            }
        }

        polygonArea = Math.abs(area) / 2.0;
        polygonPerimeter = perimeter;

        found = true;
    }

    static void dfs(int u, int pe) {

        visited[u] = true;

        for (Edge e : graph.get(u)) {

            if (found) return;

            if (e.id == pe) continue;

            int v = e.to;

            if (!visited[v]) {

                parent[v] = u;
                parentEdge[v] = e.id;
                depth[v] = depth[u] + 1;

                dfs(v, e.id);

            } else if (depth[v] < depth[u]) {

                calculateCycle(u, v, e.id);
                return;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        double totalLength = 0;

        for (int i = 0; i < n; i++) {

            int x1 = sc.nextInt();
            int y1 = sc.nextInt();

            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            Point a = new Point(x1, y1);
            Point b = new Point(x2, y2);

            sticks.add(new Stick(a, b));

            totalLength += Math.hypot(x2 - x1, y2 - y1);
        }

        buildGraph();

        int size = nodes.size();

        visited = new boolean[size];
        parent = new int[size];
        parentEdge = new int[size];
        depth = new int[size];

        Arrays.fill(parent, -1);
        Arrays.fill(parentEdge, -1);

        for (int i = 0; i < size && !found; i++) {

            if (!visited[i]) {
                dfs(i, -1);
            }
        }

        if (!found) {

            System.out.println("Abandoned");

        } else {

            double remaining = Math.max(
                0,
                totalLength - polygonPerimeter
            );

            double computerArea =
                (remaining * remaining) / (4.0 * Math.PI);

            if (polygonArea >= computerArea) {
                System.out.println("Kalyan");
            } else {
                System.out.println("Computer");
            }
        }

        sc.close();
    }
}
