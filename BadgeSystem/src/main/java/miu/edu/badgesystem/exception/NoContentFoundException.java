package miu.edu.badgesystem.exception;

import lombok.Getter;
import org.springframework.util.StringUtils;

import static miu.edu.badgesystem.exception.utils.ExceptionUtils.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Getter
public class NoContentFoundException extends RuntimeException {

    private ExceptionResponse exception;


    public NoContentFoundException(String errorMessage) {
        setErrorResponse(errorMessage, errorMessage);
    }

    private void setErrorResponse(String errorMessage, String debugMessage) {
        exception = ExceptionResponse.builder()
                .errorMessage(errorMessage)
                .debugMessage(debugMessage)
                .responseStatus(NOT_FOUND)
                .responseCode(NOT_FOUND.value())
                .timeStamp(getLocalDateTime())
                .build();
    }

    public NoContentFoundException(String errorMessage, String debugMessage) {
        setErrorResponse(errorMessage, debugMessage);
    }


}
