package aula03_designpatterns;


public class MainAul03 {
    private static Aviao boeing777X;

    public static void main(String[] args) throws Exception {
        Motor motorDireito = new MotorTurbinado("General Electric GE9X",50.0f,true);
        Motor motorEsquerdo = new MotorTurbinado("General Electric GE9X",50.0f,true);

        boeing777X = new Aviao("Fooker DR1", "Freitherr", motorDireito, motorEsquerdo);

        Passageiro p1 = new Passageiro("Gleides", "Vinente da Silva", "683.779.242-34");
        Passageiro p2 = new Passageiro("Ana Karina","Vinente Monteiro", "664.045.252-91");
        Passageiro p3 = new Passageiro("Ana Rosa","Vinente Monteiro", "685.250.420-91");
        Passageiro p4 = new Passageiro("João Gabriel","Vinente Monteiro", "524.154.264-97");
        Passageiro p5 = new Passageiro("Ana Maria","Monteiro Bezerra", "975.462.252-00");
        boeing777X.adicionarPassageiro(p1);
        boeing777X.adicionarPassageiro(p2);
        boeing777X.adicionarPassageiro(p3);
        boeing777X.adicionarPassageiro(p4);
        boeing777X.adicionarPassageiro(p5);

        System.out.println("\n----------- Localizar Passageiro ------------");
        Passageiro fulando = boeing777X.buscarPassageiroPorCPF("685.250.420-91");
        System.out.println("Passageiro localizado: " + fulando.getNome() + " " + fulando.getSobreNome());

        System.out.println("\n----------- Lista de Embarque -----------");
        boeing777X.imprimirListaDePassageiros();
        System.out.println("\n----------- Aeronave em voo ------------");
        aviaoDecolando(5);
        aviaoPousando(5);
        System.out.println("\n----------- Lista de Desembarque -----------");
        boeing777X.desembarcarPassageiros();

    }
    public static void aviaoDecolando(int acerelar){
        boeing777X.ligarMotor();
        for (int i = 1; i <= acerelar; i++) {
            //System.out.println("Aceleração Nº" + i);
            boeing777X.acelerar();
        }
    }
    public static void aviaoPousando(int desacelerar){
        for (int j = 1; j <= desacelerar; j++) {
            //System.out.println("Desaceleração Nº" + j);
            boeing777X.desacelerar();
        }
        boeing777X.desligarMotor();
    }
}
