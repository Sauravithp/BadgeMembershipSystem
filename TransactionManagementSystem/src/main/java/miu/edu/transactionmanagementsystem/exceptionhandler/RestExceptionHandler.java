package miu.edu.transactionmanagementsystem.exceptionhandler;


import lombok.extern.slf4j.Slf4j;
import miu.edu.transactionmanagementsystem.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleInternalServerException(InternalServerErrorException ex) {
        log.error("-------- NO CONTENT FOUND EXCEPTION ------");
        return buildResponseEntity(ex.getException());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleNoContentFoundException(NoContentFoundException ex) {
        log.error("-------- NO CONTENT FOUND EXCEPTION ------");
        return buildResponseEntity(ex.getException());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        log.error("------- BAD REQUEST EXCEPTION -------");
        return buildResponseEntity(ex.getException());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDataDuplicationException(DataDuplicationException ex) {
        log.error("------- DATA DUPLICATION EXCEPTION -------");
        return buildResponseEntity(ex.getException());
    }


    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse e) {
        return new ResponseEntity<>(e, e.getResponseStatus());
    }
}