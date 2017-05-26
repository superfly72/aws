package au.com.sportsbet.model.tealium;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Event implements Serializable
{

    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("selector")
    @Expose
    private String selector;
    @SerializedName("env")
    @Expose
    private String env;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("useragent")
    @Expose
    private String useragent;
    @SerializedName("new_visitor")
    @Expose
    private Boolean new_visitor;
    private final static long serialVersionUID = -812471206803121667L;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public Boolean getNew_visitor() {
        return new_visitor;
    }

    public void setNew_visitor(Boolean new_visitor) {
        this.new_visitor = new_visitor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}