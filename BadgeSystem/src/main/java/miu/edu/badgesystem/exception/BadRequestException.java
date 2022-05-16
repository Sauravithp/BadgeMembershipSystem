package miu.edu.badgesystem.exception;

import lombok.Getter;

import static miu.edu.badgesystem.exception.utils.ExceptionUtils.getLocalDateTime;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Getter
public class BadRequestException extends RuntimeException {

    private ExceptionResponse exception;

    public BadRequestException(String errorMessage) {
        super(errorMessage);
        setErrorResponse(errorMessage, errorMessage);
    }

    public BadRequestException(String errorMessage, String debugMessage) {
        setErrorResponse(errorMessage, debugMessage);
    }

    private void setErrorResponse(String errorMessage, String debugMessage) {
        exception = ExceptionResponse.builder()
                .errorMessage(errorMessage)
                .debugMessage(debugMessage)
                .responseStatus(BAD_REQUEST)
                .responseCode(BAD_REQUEST.value())
                .timeStamp(getLocalDateTime())
                .build();
    }
}
