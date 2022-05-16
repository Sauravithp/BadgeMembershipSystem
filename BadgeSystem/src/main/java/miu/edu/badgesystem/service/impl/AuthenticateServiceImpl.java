package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.config.KeycloakProvider;
import miu.edu.badgesystem.dto.request.LoginRequestDTO;
import miu.edu.badgesystem.service.AuthenticateService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final KeycloakProvider kcProvider;

    public AuthenticateServiceImpl(KeycloakProvider kcProvider) {
        this.kcProvider = kcProvider;
    }

    @Override
    public String loginUser(LoginRequestDTO requestDTO) {

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));

            Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(
                    requestDTO.getUsername(),
                    requestDTO.getPassword()
            ).build();
            AccessTokenResponse accessTokenResponse = null;
            try {
                accessTokenResponse = keycloak.tokenManager().getAccessToken();
                return accessTokenResponse.getToken();
            } catch (BadRequestException ex) {
                System.out.println("invalid account. User probably hasn't verified email.");
                return null;
            }


    }
}
