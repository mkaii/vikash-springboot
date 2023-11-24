import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // object of scanner class for user input
        AreaCalcMethods areaCalcMethods = new AreaCalcMethods();  // object of Areaclac to aaceess methods

        System.out.println( "enter 1 for triangle area enter 2 for square area enter 3 for rectangle area enter 4 for circle area");
        int choice = scanner.nextInt(); // storing the choise 1-4 b´via nextint  based on that it will enter the switch case.



        switch (choice ){ // 1-4

            case 1: {
                System.out.println("enter tringle height : "); int height = scanner.nextInt();
                System.out.println("enter tringle base");int base = scanner.nextInt();
                areaCalcMethods.triangleArea(height, base);
                break;
            }
            case 2: {
                System.out.println("enter square side: "); int side = scanner.nextInt();
                areaCalcMethods.squareArea(side);
                break;
            }
            case 3: {
                System.out.println("enter rectangle lenght : ");int lenght = scanner.nextInt();
                System.out.println("enter rectangle base : ");int base = scanner.nextInt();
                areaCalcMethods.rectangleArea(lenght, base);
                break;
            }

            case 4: {
                System.out.println("enter circle radius : ");int radius = scanner.nextInt();
                areaCalcMethods.circleArea(radius);
            }
        }
    }
}