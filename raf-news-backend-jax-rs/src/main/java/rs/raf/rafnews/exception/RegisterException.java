package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class RegisterException extends Exception{

    public RegisterException(String messageString) {
        super(messageString);
    }
    public RegisterException(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
