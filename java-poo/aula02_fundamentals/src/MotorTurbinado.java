public class MotorTurbinado extends Motor {
    public MotorTurbinado(String modelo, float potencia) {
        super(modelo, potencia);
    }

    @Override
    public void ligar() {
        System.out.println("TRUMMMMMMMâ€¦.");
        super.ligar();
    }

    @Override
    public float getPotencia() {
        return super.getPotencia() * 1.2f;
    }
}
