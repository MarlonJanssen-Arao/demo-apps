package tech.arao.polymorphism;

public abstract class Polygon implements Shape {

    private String name;
    private int numberOfSides;
    private boolean isEquilateral;
    private boolean isEquiangular;


    public Polygon(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    protected void setNumberOfSides(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public boolean isEquilateral() {
        return isEquilateral;
    }

    public void setEquilateral(boolean equilateral) {
        this.isEquilateral = equilateral;
    }

    public boolean isEquiangular() {
        return isEquiangular;
    }

    public void setEquiangular(boolean equiangular) {
        this.isEquiangular = equiangular;
    }

    public boolean isRegular() {
        return isEquilateral && isEquiangular;
    }
}
