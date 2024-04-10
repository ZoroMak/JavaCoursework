package org.example.coursework.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.coursework.clientService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/regist")
    public void createUser(HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("mail") String email,
            @RequestParam("password") String password,
            @RequestParam("date") String dateOfBirth,
                           HttpSession session
            ) throws IOException {

        if (userService.existsByEmail(email)) {
            response.sendRedirect("/regist");
            return;
        }

        userService.createUser(name, surname, email, password, dateOfBirth);
        response.sendRedirect("/home");
    }
}
