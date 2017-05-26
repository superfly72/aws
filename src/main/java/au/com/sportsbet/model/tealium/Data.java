package au.com.sportsbet.model.tealium;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Data implements Serializable
{

    @SerializedName("dom")
    @Expose
    private Dom dom;
    @SerializedName("udo")
    @Expose
    private Udo udo;
    private final static long serialVersionUID = -8199466599662302061L;

    public Dom getDom() {
        return dom;
    }

    public void setDom(Dom dom) {
        this.dom = dom;
    }

    public Udo getUdo() {
        return udo;
    }

    public void setUdo(Udo udo) {
        this.udo = udo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}