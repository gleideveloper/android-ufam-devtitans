package dependency_injection.domain.entities;

public class CarWithoutDI {
    private Engine engine = new Engine();
    public void start(){
        engine.start();
        System.out.println("Fortemente acoplado!!!");
    }
}
