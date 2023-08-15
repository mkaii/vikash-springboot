public class AreaCalculator {

    IShape shape;

    //give the specific shape to calculator
    //dependency injection happens at this point
    public void setShape(IShape shape) {
        this.shape = shape;
    }


    //print are of this specific shape
    public void printShapeArea()
    {
        shape.printArea();
    }

}
