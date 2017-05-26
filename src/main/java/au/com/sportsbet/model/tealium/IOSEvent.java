package au.com.sportsbet.model.tealium;

import au.com.sportsbet.model.BaseModel;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;

public class IOSEvent implements Serializable, BaseModel {

    private final static Logger log = LoggerFactory.getLogger(IOSEvent.class);

    private String sequenceNumber;

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    private final static long serialVersionUID = -2044949968286289250L;

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public IOSEvent fromJson(String json) {
        Gson gson = new Gson();
        log.info ("fromJson: {}", json);
        return gson.fromJson(json, IOSEvent.class);
    }

    @Override
    public ByteBuffer toByteBuffer() throws UnsupportedEncodingException {
        return ByteBuffer.wrap(this.toJson().getBytes("UTF-8"));
    }

}