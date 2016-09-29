package hello.pojo;

import javax.annotation.Generated;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
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
* No args constructor for use in serialization
* 
*/
public Datum() {
}

/**
* 
* @param es
*/
public Datum(String es) {
this.es = es;
}

/**
* 
* @return
* The es
*/
@JsonProperty("es")
public String getEs() {
return es;
}

/**
* 
* @param es
* The es
*/
@JsonProperty("es")
public void setEs(String es) {
this.es = es;
}

public Datum withEs(String es) {
this.es = es;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(es).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Datum) == false) {
return false;
}
Datum rhs = ((Datum) other);
return new EqualsBuilder().append(es, rhs.es).isEquals();
}

}
