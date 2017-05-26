package au.com.sportsbet.model.tealium;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Dates implements Serializable
{

    @SerializedName("last_event_ts")
    @Expose
    private Long last_event_ts;
    private final static long serialVersionUID = 6260105457720978185L;

    public Long getLast_event_ts() {
        return last_event_ts;
    }

    public void setLast_event_ts(Long last_event_ts) {
        this.last_event_ts = last_event_ts;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}