public class Triangle implements Shape{
private  Integer height;
private  Integer base;

    public Triangle(Integer height, Integer base) {
        this.height = height;
        this.base = base;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }



    @Override
    public void printArea() {
        System.out.println("triangle area is : " + 0.5 * height * base);
    }
}
