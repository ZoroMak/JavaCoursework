package org.example.coursework;


import org.example.coursework.auth.service.AuthenticationService;
import org.example.coursework.auth.service.UserService;
import org.example.coursework.database.model.ApplicationUser;
import org.example.coursework.database.repo.ApplicationUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @Mock
    private ApplicationUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testLoadUserByUsername_UserFound() {
        ApplicationUser user = new ApplicationUser();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        userService.loadUserByUsername("nonexistent@example.com");
    }
}
