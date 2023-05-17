package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String messageString) {
        super(messageString);
    }
    public ResourceNotFoundException(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
