package gleides.vinente.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button botaoIncrementa;
    private Button botaoProxTela;
    private TextView displayValor;
    private EditText valorEntrada;
    private EditText valorNome;
    private int acumulador;
    private int valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoIncrementa = findViewById(R.id.botaoIncrementa);
        botaoProxTela = findViewById(R.id.btnSalvar);
        displayValor = findViewById(R.id.displayValorAcumulado);
        valorEntrada = findViewById(R.id.enterNumber);
        valorNome = findViewById(R.id.enterName);
        valor = Integer.parseInt(getString(R.string.valor));
        setAcumulador(valor);

        botaoProxTela.setOnKeyListener((view, keyCode, keyEvent)->{
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                this.enviarDados(view);
                return true;
            }
            return false;
        });

        valorEntrada.setOnKeyListener((view, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                this.incrementaValor(view);
                return true;
            }
            return false;
        });

        valorEntrada.setOnFocusChangeListener((v, hasFocus)-> {
            if(hasFocus){
                v.setBackgroundResource(R.color.my_color);
                Toast.makeText(getBaseContext(), "EditText Selecionado!",
                        Toast.LENGTH_SHORT).show();
            }else{
                v.setBackgroundResource(R.color.purple_200);
            }
        });
    }

    public void enviarDados(View v){
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("vinente", valorNome.getText().toString());
        startActivity(intent);
    }

    public void incrementaValor(View v) {
        if(!valorEntrada.getText().toString().isEmpty()) {
            displayValor.setBackgroundResource(R.color.my_color);
            acumulador += Integer.parseInt(valorEntrada.getText().toString()) ;
            displayValor.setText("" + acumulador);
            valorEntrada.setText("");
        }
    }

    public Button getBotaoIncrementa() {
        return botaoIncrementa;
    }

    public void setBotaoIncrementa(Button botaoIncrementa) {
        this.botaoIncrementa = botaoIncrementa;
    }

    public TextView getDisplayValor() {
        return displayValor;
    }

    public void setDisplayValor(TextView displayValor) {
        this.displayValor = displayValor;
    }

    public int getAcumulador() {
        return acumulador;
    }

    public void setAcumulador(int acumulador) {
        this.acumulador = acumulador;
    }
}