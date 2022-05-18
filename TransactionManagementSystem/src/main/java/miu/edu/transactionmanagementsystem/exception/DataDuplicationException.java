package miu.edu.transactionmanagementsystem.exception;

import lombok.Getter;

import static miu.edu.transactionmanagementsystem.exception.utils.ExceptionUtils.getLocalDateTime;
import static miu.edu.transactionmanagementsystem.exception.utils.ExceptionUtils.toMap;
import static org.springframework.http.HttpStatus.CONFLICT;


@Getter
public class DataDuplicationException extends RuntimeException {

    private ExceptionResponse exception;

    public DataDuplicationException(String errorMessage) {
        setErrorResponse(errorMessage, errorMessage);
    }

    public DataDuplicationException(String errorMessage, String... searchParamsMap) {
        String debugMessage = "Duplicate entries with " + toMap(String.class, String.class, searchParamsMap);
        setErrorResponse(errorMessage, debugMessage);
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
