package org.ali.keycloakspring.springbootkeycloak.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String access_token;
    Integer expires_in;
    String refresh_expires_in;
    String refresh_token;
    String token_type;
}