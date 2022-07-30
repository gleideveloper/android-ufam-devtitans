package aula03_designpatterns;

import java.util.Observable;
import java.util.Observer;

public class TorreDeControle implements Observer {
    private String identificador;

    public TorreDeControle(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Torre de Controle: " + getIdentificador() + "\n" + arg.toString());
    }
}
