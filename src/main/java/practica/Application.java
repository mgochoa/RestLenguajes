package practica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import practica.Automata.AutomataFinito;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        AutomataFinito a= new AutomataFinito();
        
        
     
        
        SpringApplication.run(Application.class, args);
        
    }
}
