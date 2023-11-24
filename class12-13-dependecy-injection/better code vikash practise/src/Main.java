public class Main {
    public static void main(String[] args) {

  AreaCalc areaCalc = new AreaCalc(); //  creating AreaCalc object


  Square square1 =  new Square(20); // creating object and setting value for square
        areaCalc.setSquare(square1); // sending object into AreaCalc
        areaCalc.squareArea(); // getting the calculated area from AreaCalc class methods

        Rectangle rectangle1 = new Rectangle(23,34);
        areaCalc.setRectangle(rectangle1);
        areaCalc.rectangleArea();;

        Circle circle1 = new Circle(22);
        areaCalc.setCircle(circle1);
        areaCalc.circleArea();

        Triangle triangle1 = new Triangle(23,44);
        areaCalc.setTriangle(triangle1);
        areaCalc.triangleArea();

    }
}