package au.com.sportsbet.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by royh on 24/05/2017.
 */
public class ModelConvertorTest {

    @Test
    public void apachLogToJson() {

        String expected = {"ipAddress":"127.0.0.1","clientIdentd":"-","userID":"-","dateTimeString":"[26/Sep/2016:14:00:23 +1000]","method":"GET","endpoint":"/multibuilder/nav","protocol":"HTTP/1.0","responseCode":200,"contentSize":1034};





//127.0.0.1 - - [26/Sep/2016:14:00:23 +1000] "GET /multibuilder/nav HTTP/1.0" 200 1034 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.101 Safari/537.36"
        ApacheAccessLog log = new ApacheAccessLog("127.0.0.1", "-", "-",
                "[26/Sep/2016:14:00:23 +1000]", "GET", "/multibuilder/nav",
                "HTTP/1.0", "200", "1034");

        String json = log.toJson();
        assertEquals("blah", json);
    }

}
