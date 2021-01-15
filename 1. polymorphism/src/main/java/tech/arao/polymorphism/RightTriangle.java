package tech.arao.polymorphism;

public class RightTriangle extends Triangle {

    public RightTriangle() {
        super("Right Triangle");
        super.setEquilateral(false);
    }


    @Override
    public void setEquilateral(boolean equiangular) { }

    @Override
    public void setEquiangular(boolean equiangular) { }
}
