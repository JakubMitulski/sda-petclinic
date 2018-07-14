package pl.sda.spring.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.sda.spring.petclinic.exception.UserNotFoundException;
import pl.sda.spring.petclinic.model.ApplicationUser;
import pl.sda.spring.petclinic.repository.ApplicationUserRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        ApplicationUser applicationUser = applicationUserRepository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        GrantedAuthority ga = new SimpleGrantedAuthority("USER");

        return new User(applicationUser.getEmail(), applicationUser.getPassword(), Arrays.asList(ga));
    }
}
