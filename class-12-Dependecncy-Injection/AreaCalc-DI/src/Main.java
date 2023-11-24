import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AreaCalculator calculator = new AreaCalculator();


        // 1 -> triangle, 2-> square, 3 -> rectangle, 4 -> circle
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
            {
                int height = sc.nextInt();
                int base  = sc.nextInt();
                calculator.getTriangleArea(height,base);
                break;
            }
            case 2:
            {
                int side = sc.nextInt();
                calculator.getSquareArea(side);
                break;
            }
            case 3 :
            {
                int length = sc.nextInt();
                int base  = sc.nextInt();
                calculator.getRectangleArea(length,base);
                break;
            }
            case 4 :
            {
                int radius = sc.nextInt();
                calculator.getCircleArea(radius);
                break;
            }
        }


    }
}