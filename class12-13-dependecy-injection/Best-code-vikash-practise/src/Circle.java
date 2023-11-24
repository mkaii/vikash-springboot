public class Circle implements Shape {
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
        System.out.println("circle area is : " + radius * radius);

    }
}
