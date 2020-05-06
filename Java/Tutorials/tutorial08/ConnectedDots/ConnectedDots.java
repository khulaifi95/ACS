import javafx.scene.shape.Circle;

/**
 * The class defines the dots to be connected by lines. An object
 * consists of two lines of rows, the upper row with m, the lower with
 * n dots, each of fixed radius radius.  The dots are spread out over
 * the full size of the scene that should contain them, represented by
 * the fields xSize and ySize. In order to spread them out there will
 * be a distance between two adjacent dots in a row, represented in
 * the field dist. yPosUpper and yPosLower determine the y-positions
 * of all the dots in the upper and the lower rows, respectively. In
 * order to centre the rows we use two offsets, xUpperOffSet and
 * xLowerOffSet. All dots have the same radius.
 *
 * @author Alexandros Evangelidis, Manfred Kerber
 * @version 2018-11-28
 */
public class ConnectedDots {
    private int m;    //number of dots in the upper row
    private int n;    //number of dots in the lower row
    private double dist; //distance between 2 dots in a row
    private double yPosUpper;//y position of the upper row
    private double yPosLower;//y position of the lower row
    private double xUpperOffSet;//x-offset of the upper row to centre the dots
    private double xLowerOffSet;//x-offset of the lower row to centre the dots
    private double radius;
    private double xSize; //x dimension of the panel
    private double ySize; //y dimension of the panel

    /**
     *  @param m Number of dots in the upper row (not to be greater than 30)
     *  @param n Number of dots in the lower row (not to be greater than 30)
     *  @param radius The radius of a single dot.
     *  @param xSize The full width of the scene.
     *  @param ySize The full height of the scene.
     */
    public ConnectedDots(int m, int n, double radius,
                         double xSize, double ySize) {
        int max = Math.max(m,n);
        this.m = m ;
        this.n = n;
        /*
         *  For the computation of the distance between two dots, we
         *  take the full width of the scene and subtract the diameter
         *  of two dots (i.e., 4 * radius) and distribute equally
         *  among the (max - 1) many spaces.
         */
        this.dist = (xSize - 4 * radius)/(max - 1); 
        this.yPosUpper = 2 * radius;
        this.yPosLower = ySize - 2 * radius;
        this.xUpperOffSet = 2 * radius;             
        this.xLowerOffSet = 2 * radius;
        // The following code is to centre the dots (without it they all start left-most).
        if (n > m) {
            this.xUpperOffSet += (n - m) * this.dist/2;
        } else if (m > n) {
            this.xLowerOffSet += (m - n) * this.dist/2;
        }
        this.radius = radius;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    /**
     *  The method returns an array of a number of circles starting at
     *  a particular xOffSet, all at the same yPos, separated by the
     *  same distance dist.
     *  @param number The number of dots to be displayed.
     *  @param xOffSet The x-position of the centre of the left-most
     *  dot to be displayed.
     *  @param yPos The y-position of the centres of all the dots.
     *  @return The array containing all the dots.
     */
    public Circle[] rowOfCircles(int number, double xOffSet, double yPos) {
        Circle[] circles = new Circle[number];
        // Loop invariant: Up to i the circles are added to the Circle array.
        for (int i=0; i<circles.length; i++) {
            circles[i] = new Circle(i * dist + xOffSet, yPos, radius); 
        }
        return circles;
    }

    /**
     *  The method returns an array with all the dots in the upper row.
     *  @return The array containing all the dots in the upper row.
     */
    public Circle[] upperRowCircles() {
        return rowOfCircles(m, xUpperOffSet, yPosUpper);
    }

    /**
     *  The method returns an array with all the dots in the lower row.
     *  @return The array containing all the dots in the lower row.
     */
    public Circle[] downRowCircles() {
        return rowOfCircles(n, xLowerOffSet, yPosLower);
    }
}
