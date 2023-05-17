package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class LogoutException extends Exception{

    public LogoutException(String messageString) {
        super(messageString);
    }
    public LogoutException(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
