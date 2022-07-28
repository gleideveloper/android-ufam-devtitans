package aula02_fundamentals;

public class Passageiro {
    private String nome;
    private String sobreNome;
    private String Cpf;

    public Passageiro(String cpf) {
        Cpf = cpf;
    }

    public Passageiro(String nome, String sobreNome, String cpf) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        Cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }
}
