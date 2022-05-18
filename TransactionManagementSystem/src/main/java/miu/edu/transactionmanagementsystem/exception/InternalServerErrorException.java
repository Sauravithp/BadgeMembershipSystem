package miu.edu.transactionmanagementsystem.exception;

import lombok.Getter;

import static miu.edu.transactionmanagementsystem.exception.utils.ExceptionUtils.generateMessage;
import static miu.edu.transactionmanagementsystem.exception.utils.ExceptionUtils.getLocalDateTime;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@Getter
public class InternalServerErrorException extends RuntimeException {

    private ExceptionResponse exception;

    public InternalServerErrorException(Class clazz, String debugMessage) {
        super(generateMessage(clazz));
        exception = ExceptionResponse.builder()
                .errorMessage(generateMessage(clazz))
                .debugMessage(debugMessage)
                .responseStatus(INTERNAL_SERVER_ERROR)
                .responseCode(INTERNAL_SERVER_ERROR.value())
                .timeStamp(getLocalDateTime())
                .build();

    }

}
