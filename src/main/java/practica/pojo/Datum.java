
package practica.pojo;

import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "es"
})
public class Datum {

    @JsonProperty("es")
    private String es;

    /**
     * 
     * @return
     *     The es
     */
    @JsonProperty("es")
    public String getEs() {
        return es;
    }

    /**
     * 
     * @param es
     *     The es
     */
    @JsonProperty("es")
    public void setEs(String es) {
        this.es = es;
    }

    public Datum withEs(String es) {
        this.es = es;
        return this;
    }

}
