package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.LoginRequestDTO;
import miu.edu.badgesystem.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Override
    public String loginUser(LoginRequestDTO requestDTO) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));

            // return jwt token
            return "hello";
    }
}
