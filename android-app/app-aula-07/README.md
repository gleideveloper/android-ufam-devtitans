# Mobile Android
## ./android-app/app-aula-07: RecyclerViews e WebServicesTarefa 
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

<p align="center">
  <img src="/android-app/app-aula-07/assets/MyAppRecyclerView/Screenshot_recycleView" alt="RecyclerViews" alt="drawing" width="300"/>
</p>

###Aula07.2 - WebServicesTarefa:
