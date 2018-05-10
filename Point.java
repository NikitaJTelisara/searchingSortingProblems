package SDE_Agile;

public class Point implements Comparable<Point> {
    double x;
    double y;
    double distanceFromR;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point point) {
        double d = ((Point) point).distanceFromR;
        /* For Descending order*/
        return (int) (d - this.distanceFromR);
    }
}
