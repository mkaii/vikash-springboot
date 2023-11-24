public class Square implements Shape{

    private Integer side;

    public Square(Integer side) {
        this.side = side;
    }

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    @Override
    public void printArea() {
        System.out.println("square area is : " + side * side);
    }
}
