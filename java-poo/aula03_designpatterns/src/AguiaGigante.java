public class AguiaGigante implements Motorizavel{
    private float peso;
    private String nome;
    private float forca;
    private boolean acordada;

    public AguiaGigante(float peso, String nome, float forca) {
        this.setPeso(peso);
        this.setNome(nome);
        this.setForca(forca);
        this.setAcordada(false);
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getForca() {
        return forca;
    }

    public void setForca(float forca) {
        this.forca = forca;
    }

    public boolean isAcordada() {
        return acordada;
    }

    public void setAcordada(boolean acordada) {
        this.acordada = acordada;
    }

    @Override
    public boolean getAtivo() {
        return isAcordada();
    }

    @Override
    public float getPotencia() {
        if(isAcordada()){
            return getForca();
        }
        return 0;
    }

    @Override
    public void ligar() {
        System.out.println("Rhaaaa");
        setAcordada(true);
    }

    @Override
    public void desligar() {
        setAcordada(false);
    }
}
