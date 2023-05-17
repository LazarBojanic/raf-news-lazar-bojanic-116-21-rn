package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class GetException extends Exception{

    public GetException(String messageString) {
        super(messageString);
    }
    public GetException(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
