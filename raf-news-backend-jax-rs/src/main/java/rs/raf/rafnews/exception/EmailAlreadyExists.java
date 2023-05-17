package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class EmailAlreadyExists extends Exception {

    public EmailAlreadyExists(String messageString) {
        super(messageString);
    }
    public EmailAlreadyExists(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
