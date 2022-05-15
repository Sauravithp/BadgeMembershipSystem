package miu.edu.badgesystem.exception;

import lombok.Getter;
import miu.edu.badgesystem.exception.utils.ExceptionUtils;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class ConstraintViolationException extends RuntimeException {

    private ExceptionResponse exception;

    public ConstraintViolationException(String errorMessage, String debugMessage) {
        super(errorMessage);
        setErrorResponse(errorMessage, debugMessage);
    }

    private void setErrorResponse(String errorMessage, String debugMessage) {

        exception = ExceptionResponse.builder()
                .errorMessage(errorMessage)
                .debugMessage(debugMessage)
                .responseStatus(BAD_REQUEST)
                .responseCode(BAD_REQUEST.value())
                .timeStamp(ExceptionUtils.getLocalDateTime())
                .build();
    }
}
