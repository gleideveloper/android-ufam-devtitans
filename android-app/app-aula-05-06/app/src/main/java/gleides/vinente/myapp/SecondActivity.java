package gleides.vinente.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import gleides.vinente.myapp.data.DataBaseHandler;
import gleides.vinente.myapp.model.UsuarioLembrete;

public class SecondActivity extends AppCompatActivity {
    private Button bntSalvar;
    private CheckBox chbDropCheck;
    private TextView txvDadosUsuario;
    private TextView edtLembreteList;
    private EditText edtLembrete;

    private DataBaseHandler db;
    private String nomeChave;

    private Bundle dadosRecedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        edtLembrete = findViewById(R.id.edtLembrete);
        bntSalvar = findViewById(R.id.btnSalvar);
        edtLembreteList = findViewById(R.id.edtLembreteList);
        chbDropCheck = findViewById(R.id.chbDropCheck);
        txvDadosUsuario = findViewById(R.id.txvDadosUsuario);
        //Cria a instancia do banco SQLite
        this.db = new DataBaseHandler(this);

        //Pega os dados do usuário via Map através do Bundle
        dadosRecedido =  getIntent().getExtras();
        setNomeChave(dadosRecedido.getString("nome"));
        txvDadosUsuario.setText("Olá " + dadosRecedido.getString("nome") +
                "\nSeu saldo em conta: " + dadosRecedido.getString("acumulado") +
                "\nSua Lista de Lembretes");

        bntSalvar.setOnClickListener(view -> {
            salvarLembrete();
        });

        atualizaCaixaTexto();
    }

    public void salvarLembrete(){
        if (this.getChbDropCheck().isChecked()) {
            db.dropDB();
            db.criarTabela();
            this.finish(); //volta para a tela anterior!
        } else if(!edtLembrete.getText().toString().isEmpty()){
            UsuarioLembrete lembrete = new UsuarioLembrete();
            lembrete.setNomeCompleto(this.getNomeChave());
            lembrete.setLembrete(this.getEdtLembrete().getText().toString() + "\n");
            db.addLembrete(lembrete);
            this.atualizaCaixaTexto();
        }else{
            Toast.makeText(getBaseContext(), "Informe o lembrete!!!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void atualizaCaixaTexto() {
        String resposta = db.getLembretes(this.getNomeChave());
        this.getEdtLembreteList().setText(resposta);
    }

    public Button getBntSalvar() {
        return bntSalvar;
    }

    public void setBntSalvar(Button bntSalvar) {
        this.bntSalvar = bntSalvar;
    }

    public CheckBox getChbDropCheck() {
        return chbDropCheck;
    }

    public void setChbDropCheck(CheckBox chbDropCheck) {
        this.chbDropCheck = chbDropCheck;
    }

    public TextView getTxvDadosUsuario() {
        return txvDadosUsuario;
    }

    public void setTxvDadosUsuario(TextView txvDadosUsuario) {
        this.txvDadosUsuario = txvDadosUsuario;
    }

    public TextView getEdtLembreteList() {
        return edtLembreteList;
    }

    public void setEdtLembreteList(TextView edtLembreteList) {
        this.edtLembreteList = edtLembreteList;
    }

    public EditText getEdtLembrete() {
        return edtLembrete;
    }

    public void setEdtLembrete(EditText edtLembrete) {
        this.edtLembrete = edtLembrete;
    }

    public String getNomeChave() {
        return nomeChave;
    }

    public void setNomeChave(String nomeChave) {
        this.nomeChave = nomeChave;
    }
}