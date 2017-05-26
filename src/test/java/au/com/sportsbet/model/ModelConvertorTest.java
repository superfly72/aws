package au.com.demo.model;

import au.com.demo.model.log.ApacheAccessLog;
import au.com.demo.model.tealium.IOSEvent;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by royh on 24/05/2017.
 */
public class ModelConvertorTest {

    @Test
    public void apacheRawLogObjectToJson() {

        String expected = ("{'ipAddress':'127.0.0.1','clientIdentd':'-','userID':'-'," +
                "'dateTimeString':'[26/Sep/2016:14:00:23 +1000]','method':'GET'," +
                "'endpoint':'/multibuilder/nav','protocol':'HTTP/1.0','responseCode':200,'contentSize':1034}"
        ).replace('\'', '"');

        ApacheAccessLog log = new ApacheAccessLog("127.0.0.1", "-", "-",
                "[26/Sep/2016:14:00:23 +1000]", "GET", "/multibuilder/nav",
                "HTTP/1.0", "200", "1034");

        String json = log.toJson();
        assertEquals(expected, json);
    }

    @Test
    public void apachLogJsonToApacheAccessLogObject() {

        String json = ("{'ipAddress':'127.0.0.1','clientIdentd':'-','userID':'-'," +
                "'dateTimeString':'[26/Sep/2016:14:00:23 +1000]','method':'GET'," +
                "'endpoint':'/multibuilder/nav','protocol':'HTTP/1.0','responseCode':200,'contentSize':1034}"
        ).replace('\'', '"');

        ApacheAccessLog obj = new ApacheAccessLog().fromJson(json);
        assertEquals("IP Address", "127.0.0.1", obj.getIpAddress());
        assertEquals("ClientId", "-", obj.getClientIdentd());

    }


    @Test
    public void tealiumJsonToIOSEventObject() {

        String jsonInput = ("{\n" +
                "  'dates': {\n" +
                "    'last_event_ts': 1495694692000\n" +
                "  },\n" +
                "  'events': [\n" +
                "    {\n" +
                "      'account': 'demo',\n" +
                "      'profile': 'main',\n" +
                "      'selector': '2',\n" +
                "      'env': 'prod',\n" +
                "      'data': {\n" +
                "        'dom': {\n" +
                "          'viewport_height': 667,\n" +
                "          'referrer': '',\n" +
                "          'viewport_width': 667,\n" +
                "          'domain': 'tags.tiqcdn.com',\n" +
                "          'title': 'Tealium Mobile Webview',\n" +
                "          'query_string': 'platform=iOS&os_version=9.1&library_version=4.1.11c&timestamp_unix=1495684585',\n" +
                "          'url': 'https://tags.tiqcdn.com/utag/demo/ios-app/prod/mobile.html?platform=iOS&os_version=9.1&library_version=4.1.11c&timestamp_unix=1495684585',\n" +
                "          'pathname': '/utag/demo/ios-app/prod/mobile.html'\n" +
                "        },\n" +
                "        'udo': {\n" +
                "          'longitude': '138.559314',\n" +
                "          'latitude': '-35.019151'\n" +
                "        }\n" +
                "      },\n" +
                "      'useragent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13B143 demoIphoneNative',\n" +
                "      'new_visitor': false\n" +
                "    }\n" +
                "  ]\n" +
                "}").replace('\'', '"');

        IOSEvent iosEvent = new IOSEvent().fromJson(jsonInput);

        assertEquals("Last Event", "1495694692000", iosEvent.getDates().getLast_event_ts().toString());
        assertEquals("Env", "prod", iosEvent.getEvents().get(0).getEnv());
        assertEquals("Query String", "platform=iOS&os_version=9.1&library_version=4.1.11c&timestamp_unix=1495684585", iosEvent.getEvents().get(0).getData().getDom().getQuery_string());
        assertEquals("Longitude", "138.559314", iosEvent.getEvents().get(0).getData().getUdo().getLongitude());

    }
}
