import java.awt.*;
public class MandelbrotThread extends Thread
{   
    // variable declaration
    private int width, height, iterations, thread;
    private final int TREAD_COUNT; 
    private Double realMin, realMax, imagMin, imagMax;
    private Color colorMatrix [][];

    // constructor method
    public MandelbrotThread(Color colorMatrix[][], int iterations, int thread, Double realMin, 
            Double realMax, Double imagMin, Double imagMax, int width, int height, int TREAD_COUNT)
    {
        this.width = width;
        this.height = height;
        this.realMin = realMin;
        this.realMax = realMax;
        this.imagMin = imagMin;
        this.imagMax = imagMax;
        this.thread = thread;
        this.iterations = iterations;
        this.colorMatrix = colorMatrix;
        this.TREAD_COUNT = TREAD_COUNT;
    }

    // method to map points to canvas
    private Point mapToCanvas(Point p) {
        double xCord = p.getX()/this.width*(this.realMax - this.realMin) + this.realMin;
        double yCord = this.imagMax - p.getY()/this.height*(this.imagMax - this.imagMin);
        return new Point(xCord, yCord);
    }
    
    @Override // override the run method
    public void run() { 
        try{ 
            for(int row = thread*(this.height/this.TREAD_COUNT);
                    row < ((thread+1)*(this.height/this.TREAD_COUNT)); row++){
                for (int col = 0; col < this.width; col++){ // fill the colorMatrix
                    Point point_C = mapToCanvas(new Point(row, col));
                    colorMatrix[row][col] = Base.getBrightness(new Point(0,0), point_C, this.iterations);
                }        
            }
        } 
        catch (Exception e){  // Throwing an exception
            System.out.println ("Exception is caught at thread " + Thread.currentThread().getId()); 
        }
        System.out.println ("Thread " + Thread.currentThread().getId() + " completed");
    }
}