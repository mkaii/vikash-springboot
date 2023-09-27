public class Circle implements IShape{
    private Integer radius;

    public Circle(Integer radius) {
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }


    @Override
    public void printArea() {
        System.out.println("Circle area is : " + 3.14 * radius * radius);
    }
}
