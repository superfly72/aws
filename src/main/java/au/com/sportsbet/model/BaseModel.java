package au.com.sportsbet.model;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by royh on 25/05/2017.
 */
public interface BaseModel {

    String toJson();

    Object fromJson(String json);

    ByteBuffer toByteBuffer() throws UnsupportedEncodingException ;

}
