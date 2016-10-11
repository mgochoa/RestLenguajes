package practica;

import com.fasterxml.jackson.databind.ObjectMapper;
import practica.pojo.Pojo;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import practica.Automata.AutomataFinito;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AutomataController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private ObjectMapper mapper = new ObjectMapper();
    private AutomataFinito a = null;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
    @RequestMapping(value="/",  produces = "text/html")
    public String index() {
       return "<div style=\"padding-left:40px;padding-top:30px;\">"
               + "<h1>Practica #1 Teoria de Lenguajes: Backend </h1>"
               + "<h2>Rutas</h2>"
               + "<h3>GET:</h3>"
               + "<h3 style=\"padding-left:20px;\">  /greeting</h3>"
               + "<h3>POST:</h3>"
               + "<h3 style=\"padding-left:20px;\">  /process</h3>"
               + "<h3 style=\"padding-left:20px;\">  /try</h3>"
               + "</div>";
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    @ResponseBody
    public Pojo procesarAutomata(@RequestBody Pojo input) throws IOException {

        a = new AutomataFinito(Utils.PojoToAutomata(input));
        if (a.isAFND()) {

            a.AFNDTOAFD();           
            input = Utils.AutomataToPojo(a.automata);
            input.setAutomata(Boolean.TRUE);
            return input;

        } else {
            a.EliminarExtranos();
            a.AgruparEquivalentes();
            input = Utils.AutomataToPojo(a.automata);
            input.setAutomata(Boolean.FALSE);
            return input;
        }

    }
      @RequestMapping(value = "/try", method = RequestMethod.POST)
       @ResponseBody
       public boolean comprobarHilera(@RequestParam(value = "name", defaultValue = "0") String input) throws IOException {

      
           return a.probarHilera(input);
          // return a.comprobarHilera(input);
       

    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String[][] afndtoafd(@RequestBody Pojo input) throws IOException {
        AutomataFinito a = new AutomataFinito(Utils.PojoToAutomata(input));
        if (a.isAFND()) {
            a.AFNDTOAFD();
            return a.automata;
        } else {
            return a.automata;
        }

    }
}
