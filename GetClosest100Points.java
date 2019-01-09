package SDE_Agile;

import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;

public class GetClosest100Points {

    public static void main(String[] args) {
        Point r = new Point(3, 2);
        ArrayList<Point> x = new ArrayList<Point>();
        x.add(new Point(-1, -1));
        x.add(new Point(3, -1));
        x.add(new Point(1, 2));
        get100ClosestPoints(x, r);
    }
// or you can use bfs to get the closest 100 islands
    public static Set<Point> get100ClosestPoints(ArrayList<Point> file, Point r) {
        Set<Point> set = new TreeSet<Point>();
        for (Point p : file) {
            double a = Math.abs(p.x - r.x);
            double b = Math.abs(p.y - r.y);
            if (p.x == r.x) {
                p.distanceFromR = b;
            } else if (p.y == r.y) {
                p.distanceFromR = a;
            } else {
                double x1 = (a * a) + (b * b);
                p.distanceFromR = (Math.sqrt(x1));
            }
            set.add(p);
            if (set.size() > 100) {
                set.remove(set.iterator().next());
            }
        }
        return set;
    }
}
