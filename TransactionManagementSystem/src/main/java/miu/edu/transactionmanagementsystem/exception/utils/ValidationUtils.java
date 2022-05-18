package miu.edu.transactionmanagementsystem.exception.utils;


import miu.edu.transactionmanagementsystem.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.capitalize;


public class ValidationUtils {

    public static String getExceptionForMethodArgumentNotValid(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        List<String> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                            return capitalize(StringUtil.splitByCharacterTypeCamelCase(
                                    error.getField())) + " " + error.getDefaultMessage();
                        }
                ).collect(Collectors.toList());

        return String.join(", " , violations);
    }

}
