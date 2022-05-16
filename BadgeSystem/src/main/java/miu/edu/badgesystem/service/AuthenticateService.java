package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.LoginRequestDTO;

public interface AuthenticateService {
    String loginUser(LoginRequestDTO requestDTO);
}
