package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String messageString) {
        super(messageString);
    }
    public UserNotFoundException(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
