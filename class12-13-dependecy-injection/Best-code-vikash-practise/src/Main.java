public class Main {
    public static void main(String[] args) {
AreaCalc areaCalc = new AreaCalc();

         Triangle triangle1 = new Triangle(23,55); // crating object and setting values
        areaCalc.setShape(triangle1); // sending object to AreaCalc class -> setShape
        areaCalc.printShapeArea();// will fin the right method of respective class based on the object we sent and print calculation answer;

        Circle circle1 = new Circle(22);
        areaCalc.setShape(circle1);
        areaCalc.printShapeArea();

        Rectangle rectangle1 = new Rectangle(23,34);
        areaCalc.setShape(rectangle1);
        areaCalc.printShapeArea();

        Square square1 =  new Square(20);
        areaCalc.setShape(square1);
        areaCalc.printShapeArea();

    }
}