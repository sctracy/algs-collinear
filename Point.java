/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            double pSlope = slopeTo(p);
            double qSlope = slopeTo(q);

            if (pSlope > qSlope)        return 1;
            else if (pSlope < qSlope)   return -1;
            else                        return 0;
        }
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (this.x == that.x && this.y == that.y) {
            /* treat the slope of a degenerate line segment as negative infinity. */
            return Double.NEGATIVE_INFINITY;
        } else if (this.x == that.x) {
            /* treat the slope of a vertical line segment as positive infinity */
            return Double.POSITIVE_INFINITY;
        } else if (this.y == that.y) {
            /* treat the slope of a horizontal line segment as positive zero */
            return +0.0;
        }

        return (double) (that.y - this.y) / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y) return -1;
        if (this.y > that.y) return 1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p = new Point(0, 0);
        Point q1 = new Point(0, 2);
        Point q2 = new Point(2, 0);
        Point q3 = new Point(3, 4);
        Point q4 = new Point(4, 3);
        
        StdOut.println(p.slopeTo(q1));
        StdOut.println(p.slopeTo(q2));
        StdOut.println(p.slopeTo(q3));
        StdOut.println(p.slopeTo(q4));
        StdOut.println("==================");
        StdOut.println(p.compareTo(q1));
        StdOut.println(p.compareTo(q2));
        StdOut.println(p.compareTo(q3));
        StdOut.println(p.compareTo(q4));
    }
}
