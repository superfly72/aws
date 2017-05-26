package au.com.sportsbet.model.tealium;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Dom implements Serializable
{

    @SerializedName("viewport_height")
    @Expose
    private Integer viewport_height;
    @SerializedName("referrer")
    @Expose
    private String referrer;
    @SerializedName("viewport_width")
    @Expose
    private Integer viewport_width;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("query_string")
    @Expose
    private String query_string;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("pathname")
    @Expose
    private String pathname;
    private final static long serialVersionUID = 1559776743254317324L;

    public Integer getViewport_height() {
        return viewport_height;
    }

    public void setViewport_height(Integer viewport_height) {
        this.viewport_height = viewport_height;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Integer getViewport_width() {
        return viewport_width;
    }

    public void setViewport_width(Integer viewport_width) {
        this.viewport_width = viewport_width;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuery_string() {
        return query_string;
    }

    public void setQuery_string(String query_string) {
        this.query_string = query_string;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}