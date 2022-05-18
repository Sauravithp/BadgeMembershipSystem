package miu.edu.transactionmanagementsystem.Validator;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class LoginValidator {

    public static Boolean checkPassword(String enteredPassword, String originalPassword) {
        return BCrypt.checkpw(enteredPassword, originalPassword);
    }
}
