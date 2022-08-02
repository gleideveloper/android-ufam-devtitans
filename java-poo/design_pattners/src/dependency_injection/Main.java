package dependency_injection;

import dependency_injection.domain.entities.CarWithDI;
import dependency_injection.domain.entities.CarWithoutDI;
import dependency_injection.domain.entities.Engine;
import dependency_injection.domain.services.EngineService;

public class Main {
    public static void main(String[] args) {
        //Sem Injeção de dependencia
        CarWithoutDI carWithoutDI = new CarWithoutDI();
        carWithoutDI.start();

        //Com Injeção de dependencia usando o Construtor
        
        CarWithDI carWithDI = new CarWithDI(new Engine());
        carWithDI.start();
    }
}
