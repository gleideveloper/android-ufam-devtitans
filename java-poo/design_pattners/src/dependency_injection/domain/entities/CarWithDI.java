package dependency_injection.domain.entities;

import dependency_injection.domain.services.EngineService;

public class CarWithDI {
    private final EngineService engine;

    public CarWithDI(EngineService engine) {
        this.engine = engine;
    }

    public void start(){
        engine.start();
        System.out.println("Fracamente acoplado!!!");
    }
}
