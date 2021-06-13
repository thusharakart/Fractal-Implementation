/*
Author : THUSHARA K.A.R.
E No   : E/16/369
*/
public class Fractal{
    public static void main(String [] args) { 

        if (args.length != 0) {
            switch (args[0]) {
                case "Mandelbrot":
                    Mandelbrot.run(args); // to print the Mandelbrot set
                    break;
                case "Julia":
                    Julia.run(args); // to print the Julia set
                    break;
                default:
                    System.out.println("Wrong Usage");
                    break;
            }
        } else {
            System.out.println("No Parameters Entered");
        }
    }
}