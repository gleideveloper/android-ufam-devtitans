package gleides.vinente.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button botaoIncrementa;
    private TextView displayValor;
    private EditText valorEntrada;
    private int acumulador;
    private int valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoIncrementa = findViewById(R.id.botaoIncrementa);
        displayValor = findViewById(R.id.displayValorAcumulado);
        valorEntrada = findViewById(R.id.enterNumber);
        valor = Integer.parseInt(getString(R.string.valor));
        setAcumulador(valor);
    }

    public void incrementaValor(View view) {
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