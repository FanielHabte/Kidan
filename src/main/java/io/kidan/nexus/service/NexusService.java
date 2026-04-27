package io.kidan.nexus.service;

import io.kidan.nexus.entity.User;
import io.kidan.nexus.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NexusService {

    private final UserRepository userRepository;

    NexusService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser (User user) {
        String password = user.getPassword().trim();
        String userName = user.getUsername().trim();
        String email = user.getEmail().trim();

        user.setName(userName);
        user.setEmail(email);
        user.setPassword(encodePassword(password));

        userRepository.save(user);
    }

    private String encodePassword (String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    
    public Optional<User> getUserByEmail (String email) {
        return userRepository.findByEmail(email);
    }



}
