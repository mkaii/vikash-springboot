public class Rectangle implements Shape{
    private Integer base;
    private Integer lenght;

    public Rectangle(Integer base, Integer lenght) {
        this.base = base;
        this.lenght = lenght;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public Integer getLenght() {
        return lenght;
    }

    @Override
    public void printArea() {
        System.out.println("rectangle area is : " + lenght * base);

    }
}
