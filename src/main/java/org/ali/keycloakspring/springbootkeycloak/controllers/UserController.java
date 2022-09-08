package org.ali.keycloakspring.springbootkeycloak.controllers;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

import org.ali.keycloakspring.springbootkeycloak.configs.KeycloakProvider;
import org.ali.keycloakspring.springbootkeycloak.dto.UserDTO;
import org.ali.keycloakspring.springbootkeycloak.services.KeycloakAdminClientService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final KeycloakAdminClientService keycloakAdminClientService;
    private final KeycloakProvider keycloakProvider;

    public UserController(KeycloakAdminClientService keycloakAdminClientService, KeycloakProvider keycloakProvider) {
        this.keycloakAdminClientService = keycloakAdminClientService;
        this.keycloakProvider = keycloakProvider;
    }

    @PostMapping("/public/signup")
    public ResponseEntity<Response> createUser(UserDTO user) {
        Response response = keycloakAdminClientService.createKeycloakUser(user);
        return ResponseEntity.status(response.getStatus()).build();
    }
    
    @PostMapping("/public/login")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody UserDTO userLoginDTO) {
        Keycloak keycloak = keycloakProvider.newKeycloakBuilderWithPasswordCredentials(userLoginDTO.getEmail(), userLoginDTO.getPassword()).build();
        AccessTokenResponse accessTokenResponse = null;
        
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.ok().body(accessTokenResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }
    }
    
}
