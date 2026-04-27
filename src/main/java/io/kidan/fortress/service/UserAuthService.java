package io.kidan.fortress.service;

import io.kidan.nexus.entity.User;
import io.kidan.nexus.repository.UserRepository;
import io.kidan.nexus.service.NexusService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final NexusService nexusService;

    UserAuthService (UserRepository userRepository, NexusService nexusService) {
        this.userRepository = userRepository;
        this.nexusService = nexusService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<User> AuthenticatedUser () throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            return nexusService.getUserByEmail(currentUserEmail);
        }
        else {
            throw new UsernameNotFoundException("Authenticated User not found");
        }

    }

}
