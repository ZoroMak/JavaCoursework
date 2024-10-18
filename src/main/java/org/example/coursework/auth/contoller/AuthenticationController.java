package org.example.coursework.auth.contoller;

import org.example.coursework.database.model.ApplicationUser;
import org.example.coursework.auth.service.AuthenticationService;
import org.example.coursework.auth.dto.LoginRequestDTO;
import org.example.coursework.auth.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/registUser")
    public ApplicationUser registerUser(@RequestParam("name") String name,
                                        @RequestParam("surname") String surname,
                                        @RequestParam("mail") String email,
                                        @RequestParam("password") String password,
                                        @RequestParam("date") String dateOfBirth) {

        return authenticationService.registerUser(name, surname, email, password, dateOfBirth);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequest){
        LoginResponseDTO loginUser = authenticationService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (loginUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        return ResponseEntity.ok(loginUser);
    }
}
