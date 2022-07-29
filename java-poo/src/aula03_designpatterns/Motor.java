package aula03_designpatterns;

public class Motor implements Motorizavel{
    private String modelo;
    private float potencia;
    private boolean ativo = false;

    public Motor(String modelo, float potencia, boolean ativo) {
        this.setModelo(modelo);
        this.setPotencia(potencia);
        this.setAtivo(ativo);
    }

    @Override
    public void ligar(){
        setAtivo(true);
        System.out.println("......VRUMMMM!!");
    }

    @Override
    public void desligar(){
        setAtivo(false);
        System.out.println("mmmmm……..");
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public boolean getAtivo() {
        return false;
    }

    @Override
    public float getPotencia() {
        return potencia;
    }

    public void setPotencia(float potencia) {
        this.potencia = potencia;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
