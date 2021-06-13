import java.awt.*;
import javax.swing.*;
import java.lang.Thread;

public class Mandelbrot extends Base
{  
    // variable declaration
    private static final long serialVersionUID = 1L;
    private boolean calculationFlag;
    private final int TREAD_COUNT = 400;
    private Color colorMatrix[][];  

    // constructor method
    public Mandelbrot(Double realMin, Double realMax, Double imagMin, Double imagMax, int iterations) 
        {
        super(realMin, realMax, imagMin, imagMax, iterations);
        this.colorMatrix = new Color [this.height][this.width];
        this.calculationFlag = true;
        }

    // driver method
    public static void run(String [] args) { 
        System.out.println("Processing ...");
        Mandelbrot panel = new Mandelbrot(-1d, 1d, -1d, 1d, 1000);
        switch(args.length){ // check for the arguments
            case 1:
                break;
            case 5:
                panel = new Mandelbrot(
                    Double.parseDouble(args[1]), Double.parseDouble(args[2]), 
                    Double.parseDouble(args[3]), Double.parseDouble(args[4]), 1000);
                break;
            case 6:
                panel = new Mandelbrot(
                    Double.parseDouble(args[1]), Double.parseDouble(args[2]), 
                    Double.parseDouble(args[3]), Double.parseDouble(args[4]), 
                    Integer.parseInt(args[5]));
                break;
            default:
                System.out.println("Wrong Format!");
                System.exit(0); // somthing worng!!!
        }

		// create a frame
		JFrame frame = new JFrame("The Mandelbrot Set"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setContentPane(panel); 
		frame.pack(); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    @Override // override the paintComponent
    public void paintComponent(Graphics g) { 
		super.paintComponent(g); // call paintComponent from parent class 
        if (this.calculationFlag) {
            for (int thread = 0; thread < this.TREAD_COUNT; thread++){
                MandelbrotThread object = new MandelbrotThread( // create a tread object 
                    this.colorMatrix, this.iterations, thread,this.realMin, this.realMax,
                    this.imagMin, this.imagMax, this.width, this.height, this.TREAD_COUNT); 
                object.start();                
            }
            this.calculationFlag = false; // calculate only once
        }
        while(true){ // wait to finish
            int threadCount = Thread.activeCount(); 
            if (threadCount < 3) break; // check the active tread count               
        }
        for (int row = 0; row < this.height; row++){
            for (int col = 0; col < this.width; col++){ // paint the canvas
                printPoint((Graphics2D) g, colorMatrix[row][col], new Point(row, col));
            }
        }
        System.out.println("Process Completed"); // done!
    }

}