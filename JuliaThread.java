import java.awt.*;
public class JuliaThread extends Thread
{
    // variable declaration
    private int width, height, iterations, thread;
    private final int TREAD_COUNT; 
    private Double realMin, realMax, imagMin, imagMax;
    private Point point_C;
    private Color colorMatrix [][];

    // constructor method
    public JuliaThread(Color colorMatrix[][], int iterations, int thread, Double realMin, Double realMax,
                Double imagMin, Double imagMax, int width, int height, int TREAD_COUNT, Point point_C){
        this.width = width;
        this.height = height;
        this.realMin = realMin;
        this.realMax = realMax;
        this.imagMin = imagMin;
        this.imagMax = imagMax;
        this.thread = thread;
        this.point_C = point_C;
        this.iterations = iterations;
        this.colorMatrix = colorMatrix;
        this.TREAD_COUNT = TREAD_COUNT;
    }

    // method to map points to canvas
    private Point mapToCanvas(Point p) {
        double xCord = this.realMin + p.getX()/this.width*(this.realMax - this.realMin);
        double yCord = this.imagMax - p.getY()/this.height*(this.imagMax - this.imagMin);
        return new Point(xCord, yCord);
    }
    
    @Override // override the run method
    public void run() { 
        try{
            for (int row = thread*(this.height/this.TREAD_COUNT);
                    row < ((thread+1)*(this.height/this.TREAD_COUNT)); row++){
                for (int col = 0; col < this.width; col++){ // fill the colorMatrix
                    Point point = new Point(row, col);
                    Point c = mapToCanvas(point);
                    colorMatrix[row][col] = Base.getBrightness(c, this.point_C, this.iterations);  
                }        
            }
        } 
        catch (Exception e){  // Throwing an exception
            System.out.println ("Exception is caught at thread " + Thread.currentThread().getId()); 
        } 
        System.out.println ("Thread " + Thread.currentThread().getId() + " completed");
    }
}