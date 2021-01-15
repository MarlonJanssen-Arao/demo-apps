package tech.arao.polymorphism;

public class Circle implements Shape {

    private final Double PI = 3.14159265359;

    private Double radius;


    public Circle() { }


    public String getName() {
        return "Circle";
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getDiameter() {
        return radius * 2;
    }

    public void setDiameter(Double diameter) {
        this.radius = diameter / 2;
    }

    public Double getCircumference() {
        return 2 * PI * (radius * radius);
    }
}
