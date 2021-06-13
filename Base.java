import java.awt.*; /* java abstract window toolkit */
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class Base extends JPanel{

    // variable declaration
    private static final long serialVersionUID = 1L;
    protected int width = 800; // initialize the atribute
    protected int height = 800;
    protected int iterations;
    protected Double realMin, realMax, imagMin, imagMax;

    // constructor method
    public Base(Double realMin, Double realMax, Double imagMin, Double imagMax, int iterations) { 
        this.realMin  = realMin; 
        this.realMax = realMax; 
        this.imagMin = imagMin;
        this.imagMax = imagMax;
        this.iterations = iterations;
        setPreferredSize(new Dimension(width, height)); 
    }

    // method to print the points
    protected static void printPoint(Graphics2D frame, Color c, Point p) {
        frame.setColor(c); // set the colors
	    frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY())); 
    }
    
    // method to get the brightness
    protected static Color getBrightness(Point initial_point, Point point_C, int iterations){
        Point curr_point = initial_point; // initial point
        Point squared_point = new Point(0,0); // point to represent Z^2
		Point C = point_C;
        
        int n = 0;
        while (n < iterations){ // find the stability of the point
            squared_point = Point.complexSquare(curr_point);
            curr_point = Point.getAddition(squared_point, C);

            if (curr_point.isBounded()) break; // check for the boundry
            n++; 
        }
        if (n == iterations) return Color.BLACK;
        return new Color(0xFFEEE0 / (n+1));//give a color to the point
        // if HSB color is required uncomment the following line and comment the above line.
        //return Color.getHSBColor((float) n / iterations, 0.8f, 0.8f);
    } 
}