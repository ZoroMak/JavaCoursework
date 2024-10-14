package org.example.coursework.clientService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.coursework.clientService.repo.UserRepository;
import org.example.coursework.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void createUser(String name, String surname,
                           String email, String password,
                           String dateOfBirth){
        User user = new User();
        user.setName(name);
        user.setSurName(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthday(LocalDate.parse(dateOfBirth));

        save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User getById(int id){
        return userRepository.getById(id);
    }

    public boolean update(User User, int id){
        if (userRepository.existsById(id)) {
            User.setId(id);
            userRepository.save(User);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteById(int id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
