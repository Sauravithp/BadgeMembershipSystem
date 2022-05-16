package miu.edu.badgesystem.exception;

import lombok.Getter;

import static miu.edu.badgesystem.exception.utils.ExceptionUtils.getLocalDateTime;
import static miu.edu.badgesystem.exception.utils.ValidationUtils.getExceptionForMethodArgumentNotValid;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class MethodArgumentNotValidException extends RuntimeException {

    public static ExceptionResponse handleMethodArgumentNotValidException(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        String errorMessage = getExceptionForMethodArgumentNotValid(ex);

        return ExceptionResponse.builder()
                .errorMessage(errorMessage)
                .debugMessage(errorMessage)
                .responseStatus(BAD_REQUEST)
                .responseCode(BAD_REQUEST.value())
                .timeStamp(getLocalDateTime())
                .build();
    }
}
