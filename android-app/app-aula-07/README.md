# <a href="https://devtitans.icomp.ufam.edu.br/moodle/course/view.php?id=4" title="android-ufam-devtitans">Andoid DevTitans UFAM</a>
## Mobile Android
###./android-app/:
+ <b>/app-aula-07
  * |-> aula07: RecyclerViews e WebServicesTarefa
###Aula07.1 - RecyclerViews:
Exemplo no diagram de corte forte acoplamento entre a clase VendaService com a classe PagSeguroService, não sendo uma boa prática, sem o uso de interface.
![Alt text](src/main/resources/modelagem/AcomplamentoForte.png?raw=true "Forte Acomplamento")
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
