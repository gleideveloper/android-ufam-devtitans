package dependency_injection.domain.entities;

import dependency_injection.domain.services.EngineService;

public class Engine implements EngineService {

    @Override
    public void start() {
        System.out.println("\nEngine");
    }
}
