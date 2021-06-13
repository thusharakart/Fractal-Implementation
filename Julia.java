import java.awt.*; /* java abstract window toolkit */
import javax.swing.*;
import java.lang.Thread;

class Julia extends Base
{
    // variable declaration
    private static final long serialVersionUID = 1L;
    private boolean calculationFlag;
    private final int TREAD_COUNT = 400;
    private Point point_C = new Point(-.4, .6);
    private Color colorMatrix[][];  

    // constructor method
    public Julia(Point point_C, int iterations) {
        super(-1d, 1d, -1d, 1d, iterations);
        this.colorMatrix = new Color [this.height][this.width];
        this.point_C = point_C;
        this.calculationFlag = true;
    }

    // driver method
    public static void run(String[] args) {
        System.out.println("Processing...");
        Julia panel = new Julia(new Point(-0.4, 0.6), 1000);
        switch(args.length){ //check for the arguments
            case 1:
                break;
            case 3:
                panel = new Julia(new Point(Double.parseDouble(args[1]), 
                    Double.parseDouble(args[2])), 1000);
                break;
            case 4:
                panel = new Julia(new Point(Double.parseDouble(args[1]), 
                    Double.parseDouble(args[2])),Integer.parseInt(args[3]));
                break;
            default:
                System.out.println("Wrong Format!");
                System.exit(0); // something wrong!!!
        }

		// create a frame
		JFrame frame = new JFrame("The Julia Set"); 
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
                    JuliaThread object = new JuliaThread( // create a thread object
                        this.colorMatrix, this.iterations, thread,this.realMin, this.realMax,
                        this.imagMin, this.imagMax, this.width, this.height, this.TREAD_COUNT, this.point_C); 
                    object.start();               
                }
                this.calculationFlag = false; // calculate only once
            }
            while(true){ // wait to finish
                int threadCount = Thread.activeCount();
                if (threadCount < 3) break; // check for the treadCount              
            }
            for (int row = 0; row < this.height; row++){
                for (int col = 0; col < this.width; col++){ // paint the canvas
                    printPoint((Graphics2D) g, colorMatrix[row][col], new Point(row, col));   
                }
            }
            System.out.println("Process Completed"); // done!
        }
    }