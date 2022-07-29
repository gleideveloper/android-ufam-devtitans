package aula03_designpatterns;

import java.util.ArrayList;
import java.util.List;

public class Aviao {
    private String modelo;
    private String identificador;
    private Motorizavel motorEsquerdo;
    private Motorizavel motorDireito;
    private float altura;
    private float velocidade;
    private boolean emVoo;
    private final List<Passageiro> listaPassageiros;

    public Aviao(String modelo, String identificador, Motorizavel direito, Motorizavel esquerdo) {
        this.setModelo(modelo);
        this.setIdentificador(identificador);
        this.setMotorDireito(direito);
        this.setMotorEsquerdo(esquerdo);
        this.setAltura(0.0f);
        listaPassageiros = new ArrayList<>();
    }

    public void desembarcarPassageiros() {
        if (getVelocidade() > 0) {
            System.out.println("Protocolos de segurança não permitem embarques com a aeronave em movimento");
        } else {
            listaPassageiros.forEach(p -> {
                System.out.println(p.getNome() + " " + p.getSobreNome() + " desembarcou da aeronave!");
            });
            listaPassageiros.removeAll(getListaPassageiros());
        }
    }

    public void imprimirListaDePassageiros() {
        listaPassageiros.forEach(p -> System.out.println(
                "Nome Completo: " + p.getNome() + " " + p.getSobreNome() +
                        "\nCPF: " + p.getCpf()
        ));
    }

    public Passageiro buscarPassageiroPorCPF(String cpf) throws Exception {
        return listaPassageiros.stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst().orElseGet(() -> {
                    return null;
                });
    }

    public List<Passageiro> getListaPassageiros() {
        return listaPassageiros;
    }

    public boolean adicionarPassageiro(Passageiro passageiro) {
        if (getVelocidade() > 0) {
            System.out.println("Protocolos de segurança não permitem embarques com a aeronave em movimento");
            return false;
        }
        return listaPassageiros.add(passageiro);
    }

    public void atualizarStatusVoo() {
        if (isEmVoo()) {
            if (getVelocidade() >= 200) {
                setEmVoo(true);
                System.out.println("Estou voando...");
            } else {
                setEmVoo(false);
                System.out.println("Estou aterrisando...");
            }
        } else {
            if (getVelocidade() < 200) {
                System.out.println("Estou em solo...");
            } else {
                setEmVoo(true);
                System.out.println("Estou decolando...");
            }
        }
    }

    public void acelerar() {
        if (getEstadoMotor()) {
            setVelocidade(getVelocidade() + getPotenciaMotor());
            System.out.printf("Avião a %2.0f km/h\n", getVelocidade());
        } else {
            System.out.println("ERRO: Motor desligado");
        }
        atualizarStatusVoo();
    }

    public void desacelerar() {
        if (getEstadoMotor()) {
            if (getVelocidade() > 0) {
                setVelocidade(getVelocidade() - getPotenciaMotor());
            }
            System.out.printf("Avião a %2.0f km/h\n", getVelocidade());
        } else {
            System.out.println("ERRO: Motor desligado");
        }
        atualizarStatusVoo();
    }

    public float getPotenciaMotor() {
        long potenciaDireito = motorDireito.getAtivo() ? (long) motorDireito.getPotencia() : 0;
        long potenciaEsquerdo = motorEsquerdo.getAtivo() ? (long) motorEsquerdo.getPotencia() : 0;
        return potenciaDireito + potenciaEsquerdo;
    }

    public boolean getEstadoMotor() {
        return motorEsquerdo.getAtivo() || motorDireito.getAtivo();
    }

    public void imprimeEstadoMotor() {
        if (getEstadoMotor()) {
            if (motorEsquerdo.getAtivo() && motorDireito.getAtivo()) {
                System.out.println("Os motores estão ligados..");
            } else if (motorDireito.getAtivo()) {
                System.out.println("Somente o motor Direito está ligado");
            } else if (motorEsquerdo.getAtivo()) {
                System.out.println("Somente o motor Esquerdo está ligado");
            }
        } else {
            System.out.println("Os motores estão desligados..");
        }
    }

    public void ligarMotor() {
        motorEsquerdo.ligar();
        motorDireito.ligar();

    }

    public void desligarMotor() {
        motorEsquerdo.desligar();
        motorDireito.desligar();
    }

    public void imprimirOk() {
        System.out.println("Ok");

    }

    public Motorizavel getMotorEsquerdo() {
        return motorEsquerdo;
    }

    public void setMotorEsquerdo(Motorizavel motorEsquerdo) {
        this.motorEsquerdo = motorEsquerdo;
    }

    public Motorizavel getMotorDireito() {
        return motorDireito;
    }

    public void setMotorDireito(Motorizavel motorDireito) {
        this.motorDireito = motorDireito;
    }

    public boolean isEmVoo() {
        return emVoo;
    }

    private void setEmVoo(boolean emVoo) {
        this.emVoo = emVoo;
    }

    public float getVelocidade() {
        return Float.valueOf(velocidade).intValue();
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

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

}
