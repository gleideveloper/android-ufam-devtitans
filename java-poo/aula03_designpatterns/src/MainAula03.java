/**
 * @RepositoryGitHub: https://github.com/gleideveloper/android-ufam-devtitans
 */
public class MainAula03 {
    private static Aviao boeing737Max;

    public static void main(String[] args) throws Exception {
        Motorizavel gaviao = new AguiaGigante(1000,"Gavião",200.0f);
        Motorizavel falcao = new AguiaGigante(1000,"Falção",200.0f);

        boeing737Max = new Aviao("Boeing 737 MAX", "Boeing", gaviao, falcao);

        TorreDeControle mao = new TorreDeControle("Manaus");
        TorreDeControle sao = new TorreDeControle("São Paulo");
        boeing737Max.addObserver(mao);
        boeing737Max.addObserver(sao);

        embarquePassageiros();

        System.out.println("\n----------- Lista de Embarque -----------");
        boeing737Max.imprimirListaDePassageiros();
        System.out.println("\n----------- Aeronave em voo ------------");
        aviaoDecolando(5);
        aviaoPousando(5);
        System.out.println("\n----------- Lista de Desembarque -----------");
        boeing737Max.desembarcarPassageiros();

    }

    private static void embarquePassageiros() {
        Passageiro p1 = new Passageiro("Gleides", "Vinente da Silva", "683.779.242-34");
        Passageiro p2 = new Passageiro("Ana Karina","Vinente Monteiro", "664.045.252-91");
        Passageiro p3 = new Passageiro("Ana Rosa","Vinente Monteiro", "685.250.420-91");
        Passageiro p4 = new Passageiro("João Gabriel","Vinente Monteiro", "524.154.264-97");
        Passageiro p5 = new Passageiro("Ana Maria","Monteiro Bezerra", "975.462.252-00");
        boeing737Max.adicionarPassageiro(p1);
        boeing737Max.adicionarPassageiro(p2);
        boeing737Max.adicionarPassageiro(p3);
        boeing737Max.adicionarPassageiro(p4);
        boeing737Max.adicionarPassageiro(p5);
    }

    public static void aviaoDecolando(int acerelar){
        boeing737Max.ligarMotor();
        boeing737Max.imprimeEstadoMotor();
        for (int i = 1; i <= acerelar; i++) {
            boeing737Max.acelerar();
        }
    }

    public static void aviaoPousando(int desacelerar){
        for (int j = 1; j <= desacelerar; j++) {
            boeing737Max.desacelerar();
        }
        boeing737Max.desligarMotor();
        boeing737Max.imprimeEstadoMotor();
    }
}
