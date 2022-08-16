# Mobile Android
## ./android-app/: Aplicativo referente as aulas 05 e 06
<p align="center">
  <img src="/android-app/app-aula-05-06/assets/aula-05-06.gif" alt="Aplicativo referente as aulas 05 e 06" alt="drawing" width="300"/>
</p>

### Aula-05: Android Studio: Layout, Resources and Activities
#### Layout: <a href="https://developer.android.com/guide/topics/ui/declaring-layout?hl=pt-br" title="developer.android.com">Layout Visão Geral</a>
O layout define a estrutura de uma interface do usuário no aplicativo, como acontece na atividade. Todos os elementos do layout são criados usando a hierarquia de objetos View e ViewGroup. A View geralmente desenha algo que o usuário pode ver e com que pode interagir. Já um ViewGroup é um contêiner invisível que define a estrutura do layout para View e outros objetos ViewGroup
Os objetos View geralmente são chamados de "widgets" e podem ser uma das muitas subclasses, como Button ou TextView. Os objetos ViewGroup geralmente são chamados de layouts e podem ser de um dos muitos tipos que fornecem uma estrutura de layout diferente, como LinearLayout ou ConstraintLayout .
<p align="center">
  <img src="/android-app/app-aula-05-06/assets/viewgroup_2x.png" alt="Layout" alt="drawing" width="600"/>
</p>
Um layout pode ser declarado de duas maneiras:

* Declarar elementos da IU em XML. O Android fornece um vocabulário XML direto que corresponde às classes e subclasses de visualização, como as de widgets e layouts.
Também é possível usar o Layout Editor do Android Studio para criar o layout XML usando uma interface de arrastar e soltar.

* Instanciar elementos do layout no momento da execução. O aplicativo pode criar objetos View e ViewGroup (e processar suas propriedades) programaticamente.
Ao declarar a IU no XML, é possível separar a apresentação do seu aplicativo do código que controla o comportamento dele. O uso de arquivos XML também facilita conseguir layouts diferentes para diferentes orientações e tamanhos de tela. Isso é discutido em Compatibilidade com diferentes tamanhos de tela.

A biblioteca do Android oferece flexibilidade para usar um ou ambos os métodos para criar a IU do seu aplicativo. Por exemplo, é possível declarar os layouts padrão do aplicativo em XML e, em seguida, modificar o layout no momento da execução.


#### Parcelables e pacotes : <a href="https://developer.android.com/guide/components/activities/parcelables-and-bundles?hl=pt-br" title="developer.android.com">Como enviar dados entre atividades</a>
Quando um app cria um objeto Intent para usar em startActivity(android.content.Intent) ao iniciar uma nova atividade, o app pode transmitir parâmetros usando o método putExtra(java.lang.String, java.lang.String).

O snippet de código a seguir mostra um exemplo de como realizar essa operação.
```java
    Bundle dadosConta = new Bundle();
    dadosConta.putString("nome", valorNome.getText().toString());
    dadosConta.putString("acumulado", displayValor.getText().toString());
    Intent intent = new Intent(this, MyActivity.class);
    intent.putExtra(dadosConta);
    // ...
    startActivity(intent);
````
O SO organiza o Bundle subjacente do intent. Em seguida, o SO cria a nova atividade, separa os dados e transmite o intent para a nova atividade.

Recomendamos que você use a classe Bundle para definir primitivos conhecidos no SO em objetos Intent. A classe Bundle é altamente otimizada para empacotar e desempacotar usando lotes.

Em alguns casos, você pode precisar de um mecanismo para envio de objetos compostos ou complexos entre as atividades. Nessas situações, a classe personalizada precisa implementar o parcelable e fornecer o método writeToParcel(android.os.Parcel, int) apropriado. Ela também precisa fornecer um campo não nulo chamado CREATOR que implementa a interface Parcelable.Creator, cujo método createFromParcel() é usado para converter o Parcel de volta ao objeto atual. Para mais informações, consulte a documentação de referência do objeto Parcelable.

Ao enviar dados por meio de um intent, tenha o cuidado de limitar o tamanho dos dados a alguns KB. Enviar muitos dados pode fazer com que o sistema gere uma exceção TransactionTooLargeException.

### Aula-06: Android Studio: Intents e SQLite
<a href="https://developer.android.com/guide/components/activities/parcelables-and-bundles?hl=pt-br" title="developer.android.com">Salvar dados usando o SQLite</a>

Salvar dados em um banco de dados é ideal para dados estruturados ou que se repetem, por exemplo, os dados de contato. Esta página supõe que você esteja familiarizado com os bancos de dados SQL em geral e ajuda a começar a trabalhar com bancos de dados SQLite no Android. As APIs necessárias para usar um banco de dados no Android estão disponíveis no pacote android.database.sqlite.

#### Definir um esquema e um contrato
Um dos princípios mais importantes dos bancos de dados SQL é o esquema: uma declaração formal de como o banco de dados é organizado. O esquema é refletido nas declarações SQL usadas na criação do banco de dados. É aconselhável criar uma classe de acompanhamento, conhecida como classe de contrato, que especifica explicitamente o layout do esquema de forma sistemática e autodocumentada.

Uma classe de contrato é o contêiner das constantes que definem nomes para URIs, tabelas e colunas. A classe de contrato permite usar as mesmas constantes em outras classes no mesmo pacote. Isso permite que você altere o nome de uma coluna em um local e que a mudança se propague por todo o código.

Uma boa forma de organizar uma classe de contrato é colocar definições que sejam globais para todo o banco de dados no nível raiz da classe. Em seguida, crie uma classe interna para cada tabela. Cada classe interna enumera as colunas da tabela correspondente.
```java
public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}
````

## Implementação referente ao aplicativo
#### Model
* UsuarioLembrete
  - private int id;
  - private String nomeCompleto;
  - private String lembrete;

### Implementação Tela Principal

```java
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
        //Instacia todos os componentes da tela principal
        setContentView(R.layout.activity_main);
        botaoIncrementa = findViewById(R.id.botaoIncrementa);
        botaoProxTela = findViewById(R.id.btnSalvar);
        displayValor = findViewById(R.id.displayValorAcumulado);
        valorEntrada = findViewById(R.id.enterNumber);
        valorNome = findViewById(R.id.enterName);
        valor = Integer.parseInt(getString(R.string.valor));
        setAcumulador(valor);
        //Evento que ouvi o click no botão Enter
        valorEntrada.setOnKeyListener((view, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                this.incrementaValor(view);
                return true;
            }
            return false;
        });
        //Evento que ouvi o Focus no EditText do valor a ser acumulado
        valorEntrada.setOnFocusChangeListener((v, hasFocus)-> {
            if(hasFocus){
                v.setBackgroundResource(R.color.my_color);
                Toast.makeText(getBaseContext(), "Foi alterado a cor da caixa de texto!",
                        Toast.LENGTH_SHORT).show();
            }else{
                v.setBackgroundResource(R.color.purple_200);
            }
        });
    }
    //Recebo o focus na caixa de texto valor
    @Override
    protected void onResume() {
        super.onResume();
        valorEntrada.requestFocus();
    }

    //Enviar os dados do usuário para outra tela através Map usando um Bundle
    public void enviarDados(View v){
        if(valorNome.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(), "Informe um nome!",
                    Toast.LENGTH_SHORT).show();
        }else {
            Bundle dadosConta = new Bundle();
            dadosConta.putString("nome", valorNome.getText().toString());
            dadosConta.putString("acumulado", displayValor.getText().toString());
            valorNome.setText("");
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtras(dadosConta);
            startActivity(intent);
        }
    }
    //Acumula a soma dos valores informados
    public void incrementaValor(View v) {
        if(!valorEntrada.getText().toString().isEmpty()) {
            displayValor.setBackgroundResource(R.color.my_color);
            acumulador += Integer.parseInt(valorEntrada.getText().toString()) ;
            displayValor.setText("" + acumulador);
            valorEntrada.setText("");
        }
    }

    public void setAcumulador(int acumulador) {
        this.acumulador = acumulador;
    }
}
````
  
### Implementação Tela Secundária para receber os dados do usuário e cadastrar os lembretes

```java
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
        //Instacia todos os componentes da tela
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
            edtLembrete.requestFocus();
        });

        atualizaCaixaTexto();
    }
    //Salva os lembreta na base de dados SQLite
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
            this.getEdtLembrete().setText("");
            this.atualizaCaixaTexto();
        }else{
            Toast.makeText(getBaseContext(), "Informe o lembrete!!!",
                    Toast.LENGTH_SHORT).show();
        }
    }
    //Atualiza a caixa textview com as lista de lembretes
    private void atualizaCaixaTexto() {
        String resposta = db.getLembretes(this.getNomeChave());
        this.getEdtLembreteList().setText(resposta);
    }

    public CheckBox getChbDropCheck() {
        return chbDropCheck;
    }

    public TextView getEdtLembreteList() {
        return edtLembreteList;
    }

    public EditText getEdtLembrete() {
        return edtLembrete;
    }

    public String getNomeChave() {
        return nomeChave;
    }

    public void setNomeChave(String nomeChave) {
        this.nomeChave = nomeChave;
    }
}
````

### Implementação ChildItemAdapter

```java
public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {
    private List<ChildItem> childItemList;

    public ChildItemAdapter(List<ChildItem> childItemList) {
        this.childItemList = childItemList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        ChildItem childItem = childItemList.get(position);
        holder.childItemTitle.setText(childItem.getChildItemTitle());
        holder.childItemTitle.setText("IMDb: " + childItem.getChildItemRate());
    }

    @Override
    public int getItemCount() {
        return childItemList.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView childItemTitle;
        TextView childItemRate;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            childItemTitle = itemView.findViewById(R.id.child_item_title);
            childItemRate = itemView.findViewById(R.id.child_item_rate);
        }
    }
}
````

### Aula07.2 - WebServices usando lib Volley:  <a href="https://google.github.io/volley/request-custom.html" title="google.github.io"> Como Implementar uma solicitação personalizada </a>
<p align="center">
  <img src="/android-app/app-aula-07/assets/WebServiceVolley/webservice_volley.gif" alt="Webservice Volley." alt="drawing" width="300"/>
</p>
 
 ```java
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
        //Criando as instancias dos objetos do layout da tela principal
        button = findViewById(R.id.button);
        imvFoto = findViewById(R.id.imageView);
        txvNomeCompleto = findViewById(R.id.txvNome);
        txvEmail = findViewById(R.id.txvEmail);
        txvEndereco = findViewById(R.id.txvEndereco);
        txvDtNascimento = findViewById(R.id.txvDataNascimento);
        //Evento para ouvir o click do botão
        button.setOnClickListener(view -> jsonParseString());
    }

    private void jsonParseString() {
        String url = "https://randomuser.me/api";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    //Objeto principal do Json
                    JSONArray jArray;
                    //SubObjetos do Json
                    JSONObject jObject, jNome, jLocalizacao, jRua, jDtNascimento, jUrl;
                    //trantamento de exceção
                    try {
                        //Pegando a Lista Results
                        jArray = response.getJSONArray("results");
                        //Pegando o primeiro item da lista
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
        //Padrão de Projeto Singleton para ter uma única requisição.
        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(request);
    }
}
 ````
