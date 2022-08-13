package com.example.webservicevolley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imvFoto;
    TextView txvNomeCompleto;
    TextView txvEmail;
    TextView txvEndereco;
    TextView txvDtNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imvFoto = findViewById(R.id.imageView);
        txvNomeCompleto = findViewById(R.id.txvNome);
        txvEmail = findViewById(R.id.txvEmail);
        txvEndereco = findViewById(R.id.txvEndereco);
        txvDtNascimento = findViewById(R.id.txvDataNascimento);

        button.setOnClickListener(view -> jsonParseString());

    }

    private void jsonParseString() {
        String url = "https://randomuser.me/api";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    JSONArray jArray;
                    JSONObject jObject, jNome, jLocalizacao, jRua, jDtNascimento, jUrl;

                    try {
                        jArray = response.getJSONArray("results");
                        jObject = jArray.getJSONObject(0);
                        //Foto usuário
                        jUrl = (jObject.getJSONObject("picture"));
                        Picasso.get().load(jUrl.getString("large")).into(imvFoto);
                        //Nome Completo
                        jNome = (jObject.getJSONObject("name"));
                        txvNomeCompleto.setText(
                                "Nome Completo\n" + jNome.getString("first") + " " +
                                        jNome.getString("last")
                        );
                        //E-mail
                        txvEmail.setText(
                                "E-mail\n" + jObject.getString("email")
                        );
                        //Endereço Completo
                        jLocalizacao = (jObject.getJSONObject("location"));
                        jRua = jLocalizacao.getJSONObject("street");
                        txvEndereco.setText(
                                "Endereço\n" +
                                        jRua.getString("name") + ", " +
                                        jRua.getString("number") + ", " +
                                        jLocalizacao.getString("city") + "/" +
                                        jLocalizacao.getString("state") + ", " +
                                        jLocalizacao.getString("country")
                        );
                        //Data de Nascimento e Idade
                        jDtNascimento = (jObject.getJSONObject("dob"));
                        txvDtNascimento.setText(
                                "Data de Nascimento\n" +
                                        jDtNascimento.getString("date").split("T")[0] +
                                        "\nIdade: " + jDtNascimento.getString("age")
                        );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show();
                }
        );
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(request);
    }

}