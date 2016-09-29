package hello.pojo;

import java.util.ArrayList;
import java.util.List;
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
"data",
"acept"
})
public class AFPojo {

@JsonProperty("data")
private List<Datum> data = new ArrayList<Datum>();
@JsonProperty("acept")
private Boolean acept;

/**
* No args constructor for use in serialization
* 
*/
public AFPojo() {
}

/**
* 
* @param acept
* @param data
*/
public AFPojo(List<Datum> data, Boolean acept) {
this.data = data;
this.acept = acept;
}

/**
* 
* @return
* The data
*/
@JsonProperty("data")
public List<Datum> getData() {
return data;
}

/**
* 
* @param data
* The data
*/
@JsonProperty("data")
public void setData(List<Datum> data) {
this.data = data;
}

public AFPojo withData(List<Datum> data) {
this.data = data;
return this;
}

/**
* 
* @return
* The acept
*/
@JsonProperty("acept")
public Boolean getAcept() {
return acept;
}

/**
* 
* @param acept
* The acept
*/
@JsonProperty("acept")
public void setAcept(Boolean acept) {
this.acept = acept;
}

public AFPojo withAcept(Boolean acept) {
this.acept = acept;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(data).append(acept).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof AFPojo) == false) {
return false;
}
AFPojo rhs = ((AFPojo) other);
return new EqualsBuilder().append(data, rhs.data).append(acept, rhs.acept).isEquals();
}

}