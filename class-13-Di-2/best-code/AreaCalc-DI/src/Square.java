public class Square implements IShape{

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
        System.out.println("Square area is : " + side * side);
    }
}
