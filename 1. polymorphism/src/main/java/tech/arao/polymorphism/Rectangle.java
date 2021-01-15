package tech.arao.polymorphism;

public class Rectangle extends Polygon {

    public Rectangle() {
        this("Rectangle");
        super.setEquiangular(true);
    }

    private Rectangle(String name) {
        super(name);
    }

    @Override
    public void setEquilateral(boolean equiangular) { }

    @Override
    public void setEquiangular(boolean equiangular) { }
}
