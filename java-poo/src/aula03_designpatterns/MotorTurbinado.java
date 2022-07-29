package aula03_designpatterns;

public class MotorTurbinado extends Motor{
    public MotorTurbinado(String modelo, float potencia, boolean ativo) {
        super(modelo, potencia, ativo);
    }

    @Override
    public void ligar() {
        System.out.println("TRUMMMMMMM….");
        super.ligar();
    }

    @Override
    public float getPotencia() {
        return super.getPotencia() * 1.2f;
    }
}
