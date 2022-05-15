package miu.edu.badgesystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LoginRequestDTO {
    private String username;
    private String password;
}
