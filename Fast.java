
import java.util.Arrays;

public class Fast {
    
    public static void main(String[] args) {
        
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        //StdDraw.setPenRadius(.02);
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
        
        /* first sort the array by coordinates */
        Arrays.sort(a);
        
        Point[] aux = new Point[N];
        /* use one point each as the origin, sort the array using slope order */
        /* once all the segments related with the point is over, just leave it */
        for (int i = 0; i < N-3; i++) {
            for (int j = i; j < N; j++)
                aux[j] = a[j];
            Arrays.sort(aux, i+1, N, aux[i].SLOPE_ORDER);
            int head = i+1;
            int tail = i+2;
            
            while (tail < N) {
                /* if equal slopes encountered, just move tail forward */
                double headSlope = aux[i].slopeTo(aux[head]);
                while (tail < N && headSlope == aux[i].slopeTo(aux[tail]))
                    tail++;
                /* on slopes not equal, check if we have a segment */
                if (tail - head >= 3) {
                    /* we have a segment here */
                    /* make sure it's not a duplicate or overlapping one */
                    int k;
                    for (k = 0; k < i; k++)
                        if (aux[k].slopeTo(aux[i]) == headSlope)
                            break;
                    if (k >= i) {
                        aux[i].drawTo(aux[tail-1]);
                        String output = aux[i].toString() + " -> ";
                        for (int l = head; l < tail-1; l++)
                            output += (aux[l].toString() + " -> ");
                        output += aux[tail-1].toString();
                        StdOut.println(output);
                    }
                }
                /* move head to last not equal slope point */
                head = tail;
                tail = tail+1;
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);
    }

}
