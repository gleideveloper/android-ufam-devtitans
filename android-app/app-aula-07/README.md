# <a href="https://devtitans.icomp.ufam.edu.br/moodle/course/view.php?id=4" title="android-ufam-devtitans">Andoid DevTitans UFAM</a>
## Mobile Android
### ./android-app/:
+ <b>/app-aula-07
  * |-> aula07: RecyclerViews e WebServicesTarefa
  
### Aula07.1 - RecyclerViews: <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br" title="developer.android.com">Como criar listas dinâmicas com o RecyclerView  </a>
 
O RecyclerView facilita e torna eficiente a exibição de grandes conjuntos de dados. Você fornece os dados e define a aparência de cada item, e a biblioteca RecyclerView, quando necessário, cria os elementos dinamicamente.

O RecyclerView, como o nome indica, recicla esses elementos individuais. Quando um item rola para fora da tela, o RecyclerView não destrói a visualização dele. Em vez disso, o RecyclerView reutiliza a visualização para novos itens que passaram a aparecer na tela. Isso melhora muito o desempenho, aperfeiçoando a capacidade de resposta do app e reduzindo o consumo de energia.

### Classes principais
Diversas classes diferentes funcionam juntas para criar sua lista dinâmica.
* RecyclerView é o ViewGroup que contém as visualizações correspondentes aos seus dados. Ela é uma visualização em si, então adicione a RecyclerView ao layout da mesma forma que você adicionaria qualquer outro elemento da IU.
* Cada elemento individual da lista é definido por um objeto fixador de visualização. Quando o fixador de visualização é criado, ele não tem dados associados a si mesmo. Depois que o fixador de visualização é criado, o RecyclerView o vincula aos dados. Para definir o fixador de visualização, estenda RecyclerView.ViewHolder.
* RecyclerView é o ViewGroup que contém as visualizações correspondentes aos seus dados. Ela é uma visualização em si, então adicione a RecyclerView ao layout da mesma forma que você adicionaria qualquer outro elemento da IU.

* O gerenciador de layout organiza os elementos individuais na sua lista. Você pode usar um dos gerenciadores de layout fornecidos pela biblioteca RecyclerView ou pode definir seu próprio gerenciador. Todos os gerenciadores de layout são baseados na classe abstrata LayoutManager da biblioteca.

### Etapas para implementar o RecyclerView
Se você quiser usar o RecyclerView, é necessário realizar algumas ações. Elas serão discutidas em detalhes nas seções a seguir.

* Em primeiro lugar, decida como será a lista ou a grade. Em geral, você poderá usar um dos gerenciadores de layout padrão da biblioteca RecyclerView.

* Crie a aparência e o comportamento de cada elemento da lista. Com base nisso, estenda a classe ViewHolder. A versão do ViewHolder fornece toda a funcionalidade para os itens da lista. O fixador de visualização é um wrapper em torno de uma View, e essa visualização é gerenciada por RecyclerView.

Defina o Adapter que associa seus dados às visualizações ViewHolder.


<p align="center">
  <img src="/android-app/app-aula-07/assets/MyAppRecyclerView/recycler_view.gif" alt="RecyclerViews" alt="drawing" width="300"/>
</p>

###Aula07.2 - WebServicesTarefa:
```java
public class VendaService {

    public void registrar(Venda venda, String numeroCartao){
        BigDecimal valorTotal = venda.getPricoUnitario().multiply(new BigDecimal(venda.getQuantidade()));
        System.out.print("[Venda] Registrando venda de %s no valor total de %f...\n");

        //Forte acomplamento entre a clase VendaService com a classe PagSeguroService
        PagSeguroService pagSeguroService = new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3");
        pagSeguroService.efetuarPagamento(numeroCartao, valorTotal);
    }
}
````

<p align="center">
  <img src="/android-app/app-aula-07/assets/MyAppRecyclerView/Screenshot_recycleView" alt="RecyclerViews" alt="drawing" width="300"/>
</p>
