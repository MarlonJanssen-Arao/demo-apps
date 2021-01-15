package tech.arao.polymorphism;

public class Triangle extends Polygon {

    public Triangle() {
        this("Triangle");
        this.setNumberOfSides(3);
    }

    protected Triangle(String name) {
        super(name);
    }


    @Override
    protected void setNumberOfSides(int numberOfSides) {
    }

    @Override
    public void setEquilateral(boolean areAllEqual) {
        super.setEquilateral(areAllEqual);
        super.setEquiangular(areAllEqual);
    }

    @Override
    public void setEquiangular(boolean areAllEqual) {
        super.setEquiangular(areAllEqual);
        super.setEquilateral(areAllEqual);
    }
}
