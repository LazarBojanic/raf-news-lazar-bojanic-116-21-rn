package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class AddException extends Exception{
    public AddException(String messageString) {
        super(messageString);
    }
    public AddException(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
