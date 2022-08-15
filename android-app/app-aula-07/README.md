# Mobile Android
## ./android-app/app-aula-07: RecyclerViews e WebServices usando lib Volley
### Aula07.1 - RecyclerViews: <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br" title="developer.android.com">Como criar listas dinâmicas com o RecyclerView  </a>
 
O RecyclerView facilita e torna eficiente a exibição de grandes conjuntos de dados. Você fornece os dados e define a aparência de cada item, e a biblioteca RecyclerView, quando necessário, cria os elementos dinamicamente. Como o nome indica, recicla esses elementos individuais. Quando um item rola para fora da tela, o RecyclerView não destrói a visualização dele. Em vez disso, o RecyclerView reutiliza a visualização para novos itens que passaram a aparecer na tela. Isso melhora muito o desempenho, aperfeiçoando a capacidade de resposta do app e reduzindo o consumo de energia.

### Etapas para implementar o RecyclerView
Se você quiser usar o RecyclerView, é necessário realizar algumas ações. Elas serão discutidas em detalhes nas seções a seguir.

* Em primeiro lugar, decida como será a lista ou a grade. Em geral, você poderá usar um dos gerenciadores de layout padrão da biblioteca RecyclerView.

* Crie a aparência e o comportamento de cada elemento da lista. Com base nisso, estenda a classe ViewHolder. A versão do ViewHolder fornece toda a funcionalidade para os itens da lista. O fixador de visualização é um wrapper em torno de uma View, e essa visualização é gerenciada por RecyclerView.

Defina o Adapter que associa seus dados às visualizações ViewHolder.


<p align="center">
  <img src="/android-app/app-aula-07/assets/MyAppRecyclerView/recycler_view.gif" alt="RecyclerViews" alt="drawing" width="300"/>
</p>

### Implementação RecyclerView

#### Model
* ParentItem
  - private String parentItemTitle;
  - private List<ChildItem> childItemList;
* ChildItem
  - private String childItemTitle;
  - private int childItemRate;
 
```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView parentRecyclerView = findViewById(R.id.parent_recyclerview);
        //Instacia o gerenciador e o adapter principal da list pai.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(parentItemList());
        //Adiciona o adpater na RecyclerView
        parentRecyclerView.setAdapter(parentItemAdapter);
        //Adiciona a RecyclerView no layout gerenciador
        parentRecyclerView.setLayoutManager(layoutManager);
    }
    //Adicionar os itens pais na lista
    private List<ParentItem> parentItemList() {
        List<ParentItem> parentItemList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            parentItemList.add(new ParentItem("Titulo Faixa " + i, childItemList()));
        }
        return parentItemList;
    }
    //Adicionar os itens filhos na lista
    private List<ChildItem> childItemList() {
        List<ChildItem> childItemList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            childItemList.add(new ChildItem("Item da faixa" + i, 10));
        }
        return childItemList;
    }
}
````
### Implementação ParentItemAdapter

```java
public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ParentItem> itemList;

    public ParentItemAdapter(List<ParentItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        ParentItem parentItem = itemList.get(position);
        holder.parentItemTitle.setText(parentItem.getParentItemTitle());

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.childRecycleView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());

        ChildItemAdapter childItemAdapter = new ChildItemAdapter(parentItem.getChildItemList());
        holder.childRecycleView.setLayoutManager(layoutManager);
        holder.childRecycleView.setAdapter(childItemAdapter);
        holder.childRecycleView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ParentViewHolder extends RecyclerView.ViewHolder {
        private final TextView parentItemTitle;
        private final RecyclerView childRecycleView;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            parentItemTitle = itemView.findViewById(R.id.parent_item_title);
            childRecycleView = itemView.findViewById(R.id.child_recyclerview);
        }
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
