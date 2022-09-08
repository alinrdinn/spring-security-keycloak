package org.ali.keycloakspring.springbootkeycloak.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    String email;
    String password;
    String username;
}
