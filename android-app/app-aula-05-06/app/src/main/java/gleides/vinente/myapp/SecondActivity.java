package gleides.vinente.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView txvDadosConta;
    private TextView edtLembreteList;
    private Button bntSalvar;
    private EditText edtLembrete;
    private CheckBox chbDropCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        edtLembrete = findViewById(R.id.edtLembrete);
        bntSalvar = findViewById(R.id.btnSalvar);
        edtLembreteList = findViewById(R.id.edtLembreteList);
        chbDropCheck = findViewById(R.id.chbDropCheck);
        txvDadosConta = findViewById(R.id.txvDadosConta);
        Bundle dadosRecedido =  getIntent().getExtras();
        txvDadosConta.setText("Olá " + dadosRecedido.getString("nome") +
                "\nSeu saldo em conta: " + dadosRecedido.getString("acumulado"));
    }

}