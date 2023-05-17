package rs.raf.rafnews.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.util.Serializer;

public class TokenGenerateException extends Exception{

    public TokenGenerateException(String messageString) {
        super(messageString);
    }
    public TokenGenerateException(ExceptionMessage exceptionMessage) throws JsonProcessingException {
        super(Serializer.serialize(exceptionMessage));
    }
}
