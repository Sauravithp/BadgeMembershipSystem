package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.Validator.LoginValidator;
import miu.edu.badgesystem.config.KeycloakProvider;
import miu.edu.badgesystem.dto.request.LoginRequestDTO;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.repository.UserRepository;
import miu.edu.badgesystem.service.AuthenticateService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    UserRepository userRepository;
    private final KeycloakProvider kcProvider;

    public AuthenticateServiceImpl(KeycloakProvider kcProvider) {
        this.kcProvider = kcProvider;
    }

    @Override
    public String loginUser(LoginRequestDTO requestDTO) {
        var user = userRepository.getUser(requestDTO.getUsername()).orElseThrow(()->{
            throw new NoContentFoundException("User is not found");
        });
        String token = null;
        if(LoginValidator.checkPassword(requestDTO.getPassword(), user.getPassword())){
            token = getJwtToken(requestDTO);
        }else {
            throw new NoContentFoundException("User is not found");
        }
        return token;
    }

    private String getJwtToken(LoginRequestDTO requestDTO) {
        try {
            Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(
                    requestDTO.getUsername(),
                    requestDTO.getPassword()
            ).build();
            AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return accessTokenResponse.getToken();
        } catch (BadRequestException ex) {
            System.out.println("invalid account. User probably hasn't verified username.");
            return null;
        }
    }
}
