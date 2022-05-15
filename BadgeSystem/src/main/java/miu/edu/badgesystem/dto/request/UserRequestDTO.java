package miu.edu.badgesystem.dto.request;

import lombok.Data;

@Data
public class UserRequestDTO {

    private Long id;

    private String username;

    private String password;

    private String role;
}
