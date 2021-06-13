class Point { 

    private double x, y; 
    
    public Point(double x, double y) { 
        this.x = x; 
        this.y = y; 
    }

    public double getX() { return this.x; } 
    public double getY() { return this.y; } 

    public static Point complexSquare(Point p){
        return new Point(p.getX()*p.getX() - p.getY()*p.getY(), 2*p.getX()*p.getY());
    }

    public static Point getAddition(Point p1, Point p2){
        return new Point(p1.getX()+p2.getX(), p1.getY()+p2.getY());
    }

    public boolean isBounded(){
        return((this.getX() * this.getX() + this.getY() * this.getY()) > 4);
    }
}
	