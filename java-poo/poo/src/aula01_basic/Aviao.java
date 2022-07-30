package aula01_basic;

public class Aviao {
    private String modelo;
    private String identificador;
    private boolean motor;
    private float altura;
    private float velocidade;
    private boolean emVoo;

    public Aviao(String modelo, String identificador) {
        this.setModelo(modelo);
        this.setIdentificador(identificador);
        this.setMotor(false);
        this.setAltura(0.0f);
        this.emVoo = false;
    }

    public void atualizarStatusVoo() {
        if (isEmVoo()) {
            if (getVelocidade() >= 200) {
                System.out.println("Estou voando...");
            } else {
                setEmVoo(false);
                System.out.println("Estou aterrisando...");
            }
        } else {
            if (getVelocidade() < 200) {
                System.out.println("Estou em solo...");
            }else if (getVelocidade() >= 200) {
                setEmVoo(true);
                System.out.println("Estou decolando...");
            }
        }
    }

    public void acelerar() {
        if (getEstadoMotor()) {
            setVelocidade(getVelocidade() + 50.0f);
            System.out.println("Avião a " + getVelocidade() + " km/h");
        } else {
            System.out.println("ERRO: Motor desligado");
        }
        atualizarStatusVoo();
    }

    public void desacelerar() {
        if (getEstadoMotor()) {
            if (getVelocidade() > 0.0f) {
                setVelocidade(getVelocidade() - 50.0f);
            }
            System.out.println("Avião a " + getVelocidade() + " km/h");
        } else {
            System.out.println("ERRO: Motor desligado");
        }
        atualizarStatusVoo();
    }

    public boolean getEstadoMotor() {
        return isMotor();
    }

    public void imprimeEstadoMotor() {
        if (isMotor()) {
            System.out.println("O motor esta ligado..");
        } else {
            System.out.println("O motor está desligado..");
        }
    }

    public void ligarMotor() {
        setMotor(true);
        System.out.println("Vrummmmmmm");
    }

    public void desligarMotor() {
        setMotor(false);
    }

    public void imprimirOk() {
        System.out.println("Ok");

    }

    public boolean isEmVoo() {
        return emVoo;
    }

    private void setEmVoo(boolean emVoo) {
        this.emVoo = emVoo;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public boolean isMotor() {
        return motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

}
