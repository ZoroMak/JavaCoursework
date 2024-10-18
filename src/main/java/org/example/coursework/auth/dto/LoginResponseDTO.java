package org.example.coursework.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.coursework.database.model.ApplicationUser;

@Getter
@Setter
public class LoginResponseDTO {
    private ApplicationUser user;
    private String jwt;

    public LoginResponseDTO(ApplicationUser user, String jwt){
        this.user = user;
        this.jwt = jwt;
    }
}
