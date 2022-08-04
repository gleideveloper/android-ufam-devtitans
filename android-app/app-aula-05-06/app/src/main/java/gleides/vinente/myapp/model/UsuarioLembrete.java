package gleides.vinente.myapp.model;

public class UsuarioLembrete {
    private int id;
    private String nomeCompleto;
    private String lembrete;

    public UsuarioLembrete(int id, String nomeCompleto, String lembrete) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.lembrete = lembrete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getLembrete() {
        return lembrete;
    }

    public void setLembrete(String lembrete) {
        this.lembrete = lembrete;
    }
}
