import java.util.ArrayList;
import java.util.List;

public class Aviao {
    private String modelo;
    private String identificador;
    private Motor motorEsquerdo;
    private Motor motorDireito;
    private float altura;
    private float velocidade;
    private boolean emVoo;
    private final List<Passageiro> listaPassageiros;
    private String statusVelocidade;

    public Aviao(String modelo, String identificador, Motor direito, Motor esquerdo) {
        this.setModelo(modelo);
        this.setIdentificador(identificador);
        this.setMotorDireito(direito);
        this.setMotorEsquerdo(esquerdo);
        this.setAltura(0.0f);
        this.emVoo = false;
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
        String statusVoo = null;
        if (isEmVoo()) {
            if (getVelocidade() >= getPotenciaMotor() * 1.1) {
                statusVoo = "Estou voando...";
            } else {
                setEmVoo(false);
                statusVoo = "Estou aterrisando...";
            }
        } else {
            if (getVelocidade() < getPotenciaMotor() * 1.1) {
                statusVoo = "Estou em solo...";
            } else{
                setEmVoo(true);
                statusVoo = "Estou decolando...";
            }
        }
        System.out.println(statusVelocidade + " >> " + statusVoo);
    }

    public void acelerar() {
        if (getEstadoMotor()) {
            setVelocidade(getVelocidade() + getPotenciaMotor());
            statusVelocidade = "Avião a " + getVelocidade() + " km/h";
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
            statusVelocidade = "Avião a " + getVelocidade() + " km/h";
        } else {
            System.out.println("ERRO: Motor desligado");
        }
        atualizarStatusVoo();
    }

    public float getPotenciaMotor() {
        long potenciaDireito = motorDireito.isAtivo() ? (long) motorDireito.getPotencia() : 0;
        long potenciaEsquerdo = motorEsquerdo.isAtivo() ? (long) motorEsquerdo.getPotencia() : 0;
        return potenciaDireito + potenciaEsquerdo;
    }

    public boolean getEstadoMotor() {
        return motorEsquerdo.isAtivo() || motorDireito.isAtivo();
    }

    public void imprimeEstadoMotor() {
        if (getEstadoMotor()) {
            if (motorEsquerdo.isAtivo() && motorDireito.isAtivo()) {
                System.out.println("Os motores estão ligados..");
            } else if (motorDireito.isAtivo()) {
                System.out.println("Somente o motor Direito está ligado");
            } else if (motorEsquerdo.isAtivo()) {
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

    public Motor getMotorEsquerdo() {
        return motorEsquerdo;
    }

    public void setMotorEsquerdo(Motor motorEsquerdo) {
        this.motorEsquerdo = motorEsquerdo;
    }

    public Motor getMotorDireito() {
        return motorDireito;
    }

    public void setMotorDireito(Motor motorDireito) {
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
