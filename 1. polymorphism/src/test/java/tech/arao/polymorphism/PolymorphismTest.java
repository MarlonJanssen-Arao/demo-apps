package tech.arao.polymorphism;

import org.junit.Test;

public class PolymorphismTest {

    @Test
    public void test() {
        System.out.println("SHAPES");
        System.out.println("==========");
        Circle circle = new Circle();
        circle.setRadius(3.14159265359);
        System.out.println(circle.getName());
        System.out.println(circle.getRadius());
        System.out.println(circle.getDiameter());
        System.out.println(circle.getCircumference());
        System.out.println("----------");
        Triangle triangle = new Triangle();
        triangle.setEquiangular(true);
        System.out.println(triangle.getName());
        System.out.println(triangle.isEquiangular());
        System.out.println(triangle.isEquilateral());
        System.out.println("----------");
        Triangle equilateralTriangle = new EquilateralTriangle();
        equilateralTriangle.setEquiangular(true);
        System.out.println(equilateralTriangle.getName());
        System.out.println(equilateralTriangle.isEquiangular());
        System.out.println(equilateralTriangle.isEquilateral());
        System.out.println("----------");
        Triangle rightTriangle = new RightTriangle();
        System.out.println(rightTriangle.getName());
        System.out.println(rightTriangle.isEquiangular());
        System.out.println(rightTriangle.isEquilateral());
        System.out.println("----------");
        Rectangle rectangle = new Rectangle();
        System.out.println(rectangle.getName());
        System.out.println(rectangle.isEquiangular());
        System.out.println(rectangle.isEquilateral());
        System.out.println("----------");
    }
}
