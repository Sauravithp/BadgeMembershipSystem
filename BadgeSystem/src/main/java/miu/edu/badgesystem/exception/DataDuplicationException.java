package miu.edu.badgesystem.exception;

import lombok.Getter;

import static miu.edu.badgesystem.exception.utils.ExceptionUtils.getLocalDateTime;
import static miu.edu.badgesystem.exception.utils.ExceptionUtils.toMap;
import static org.springframework.http.HttpStatus.CONFLICT;


@Getter
public class DataDuplicationException extends RuntimeException {

    private ExceptionResponse exception;

    public DataDuplicationException(String errorMessage) {
        setErrorResponse(errorMessage, errorMessage);
    }


    private void setErrorResponse(String errorMessage, String debugMessage) {
        exception = ExceptionResponse.builder()
                .errorMessage(errorMessage)
                .debugMessage(debugMessage)
                .responseStatus(CONFLICT)
                .responseCode(CONFLICT.value())
                .timeStamp(getLocalDateTime())
                .build();
    }

}
