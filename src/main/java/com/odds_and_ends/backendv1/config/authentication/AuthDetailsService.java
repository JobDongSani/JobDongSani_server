package com.odds_and_ends.backendv1.config.authentication;

import com.odds_and_ends.backendv1.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(Long.parseLong(username))
                .map(AuthDetails::new)
                .orElseThrow(RuntimeException::new);
        // TODO: 2021/11/24 Exception Handler 만들어서 Custom Exception
    }
}
