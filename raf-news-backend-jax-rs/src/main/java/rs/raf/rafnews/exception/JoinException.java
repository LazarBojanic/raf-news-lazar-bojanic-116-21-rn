package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class JoinException extends Exception{
    public JoinException(String messageString) {
        super(messageString);
    }
    public JoinException(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
