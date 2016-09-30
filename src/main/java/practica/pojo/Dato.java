
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
    "data",
    "acept"
})
public class Dato {

    @JsonProperty("data")
    private List<Datum> data = new ArrayList<Datum>();
    @JsonProperty("acept")
    private Boolean acept;

    /**
     * 
     * @return
     *     The data
     */
    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Dato withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    /**
     * 
     * @return
     *     The acept
     */
    @JsonProperty("acept")
    public Boolean getAcept() {
        return acept;
    }

    /**
     * 
     * @param acept
     *     The acept
     */
    @JsonProperty("acept")
    public void setAcept(Boolean acept) {
        this.acept = acept;
    }

    public Dato withAcept(Boolean acept) {
        this.acept = acept;
        return this;
    }

}
