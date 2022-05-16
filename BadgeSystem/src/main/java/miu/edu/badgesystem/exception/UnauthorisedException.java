package miu.edu.badgesystem.exception;

import lombok.Getter;

import static miu.edu.badgesystem.exception.utils.ExceptionUtils.generateMessage;
import static miu.edu.badgesystem.exception.utils.ExceptionUtils.getLocalDateTime;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
public class UnauthorisedException extends RuntimeException {

    private ExceptionResponse exception;

    public UnauthorisedException(Class clazz, String debugMessage) {
        super(generateMessage(clazz));
        exception = ExceptionResponse.builder()
                .errorMessage(generateMessage(clazz))
                .debugMessage(debugMessage)
                .responseStatus(UNAUTHORIZED)
                .responseCode(UNAUTHORIZED.value())
                .timeStamp(getLocalDateTime())
                .build();

    }

    public UnauthorisedException( String debugMessage) {
        exception = ExceptionResponse.builder()
                .errorMessage(debugMessage)
                .debugMessage(debugMessage)
                .responseStatus(UNAUTHORIZED)
                .responseCode(UNAUTHORIZED.value())
                .timeStamp(getLocalDateTime())
                .build();

    }

}
