package au.com.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by royh on 22/05/2017.
 */
@Component
@ConfigurationProperties(prefix="aws.kinesis.security")
public class SecurityConfig {

    private String profile;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
