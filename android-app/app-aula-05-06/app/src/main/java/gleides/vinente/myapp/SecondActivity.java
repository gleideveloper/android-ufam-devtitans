package gleides.vinente.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView nomeUsuario;
    private Button bntSalvar;
    private EditText lembrete;
    private CheckBox dropCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nomeUsuario = findViewById(R.id.edtNomeUsuario);
        bntSalvar = findViewById(R.id.btnSalvar);
        lembrete = findViewById(R.id.edtLembrete);
        dropCheck = findViewById(R.id.checkBox);
        Bundle dadosRecedido =  getIntent().getExtras();
        lembrete.setText("Olá " + dadosRecedido.getString("nome") +
                "\nSeu saldo em conta: " + dadosRecedido.getString("acumulado"));
    }

}