
public class Brute {
	public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        
        Point[] a = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            a[i] = p;
            p.draw();
        }
        
        for (int i = 0; i < N; i++)
        	for (int j = i+1; j < N; j++)
        		for (int k = j+1; k < N; k++)
        			for (int l = k+1; l < N; l++) {
        				if (a[i].slopeTo(a[j]) == a[i].slopeTo(a[k]) &&
        						a[i].slopeTo(a[j]) == a[i].slopeTo(a[l])) {
        					StdOut.println(a[i].toString() + " -> " + a[j].toString() + " -> " + a[k].toString() + " -> " + a[l].toString());
        					a[i].drawTo(a[l]);
        				}
        			}
        
        // display to screen all at once
        StdDraw.show(0);
    }
}
