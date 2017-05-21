package au.com.sportsbet;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by roy on 21/05/17.
 */
public class Utils {

    private static final Random RANDOM = new Random();

    /**
     * @return A random unsigned 128-bit int converted to a decimal string.
     */
    public static String randomExplicitHashKey() {
        return new BigInteger(128, RANDOM).toString(10);
    }

}
