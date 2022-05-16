package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.UserRequestDTO;
import miu.edu.badgesystem.model.User;

public interface UserService {
    User save(UserRequestDTO userRequestDTO);
}
