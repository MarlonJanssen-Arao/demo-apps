package tech.arao.polymorphism;

public class EquilateralTriangle extends Triangle {

    public EquilateralTriangle() {
        super("Equilateral Triangle");
        super.setEquilateral(true);
    }


    @Override
    public void setEquilateral(boolean equiangular) { }

    @Override
    public void setEquiangular(boolean equiangular) { }
}
