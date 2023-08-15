import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        AreaCalculator calculator = new AreaCalculator();

        Square sq1 = new Square(10);

        calculator.setSquare(sq1);
        calculator.getSquareArea();

        //triangle :

        Triangle tr1 = new Triangle(20,15);

        calculator.setTriangle(tr1);

        calculator.getTriangleArea();

        //client comes again and says he needs support for Circle and Rectangle

        Rectangle r1 = new Rectangle(2,10);
        calculator.setRectangle(r1);

        Circle c1 = new Circle(10);
        calculator.setCircle(c1);

        calculator.getRectangleArea();
        calculator.getCircleArea();





    }
}