package gleides.vinente.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textRecebido = findViewById(R.id.txvMensagem);
        Button btnBack = findViewById(R.id.btnSalvar);
        textRecebido.setText("Olá " + getIntent().getStringExtra("vinente"));
    }

    public void voltar(View v){
        finish();
    }
}