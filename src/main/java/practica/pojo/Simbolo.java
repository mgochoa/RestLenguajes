
package practica.pojo;

import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "sim"
})
public class Simbolo {

    @JsonProperty("sim")
    private String sim;

    /**
     * 
     * @return
     *     The sim
     */
    @JsonProperty("sim")
    public String getSim() {
        return sim;
    }

    /**
     * 
     * @param sim
     *     The sim
     */
    @JsonProperty("sim")
    public void setSim(String sim) {
        this.sim = sim;
    }

    public Simbolo withSim(String sim) {
        this.sim = sim;
        return this;
    }

}
