
package practica.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "datos",
    "simbolos",
    "automata"
})
public class Pojo {

    @JsonProperty("datos")
    private List<Dato> datos = new ArrayList<Dato>();
    @JsonProperty("simbolos")
    private List<Simbolo> simbolos = new ArrayList<Simbolo>();
    @JsonProperty("automata")
    private boolean automata;
    
    /**
     * 
     * @return
     *     The datos
     */
    @JsonProperty("datos")
    public List<Dato> getDatos() {
        return datos;
    }

    /**
     * 
     * @param datos
     *     The datos
     */
    @JsonProperty("datos")
    public void setDatos(List<Dato> datos) {
        this.datos = datos;
    }

    public Pojo withDatos(List<Dato> datos) {
        this.datos = datos;
        return this;
    }

    /**
     * 
     * @return
     *     The simbolos
     */
    @JsonProperty("simbolos")
    public List<Simbolo> getSimbolos() {
        return simbolos;
    }

    /**
     * 
     * @param simbolos
     *     The simbolos
     */
    @JsonProperty("simbolos")
    public void setSimbolos(List<Simbolo> simbolos) {
        this.simbolos = simbolos;
    }

    public Pojo withSimbolos(List<Simbolo> simbolos) {
        this.simbolos = simbolos;
        return this;
    }
    @JsonProperty("automata")
    public Boolean getAutomata() {
        return automata;
    }

    /**
     * 
     * @param automata
     *     The automata
     */
    @JsonProperty("automata")
    public void setAutomata(Boolean automata) {
        this.automata = automata;
    }

    public Pojo withAutomata(Boolean automata) {
        this.automata = automata;
        return this;
    }

}
