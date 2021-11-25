package com.odds_and_ends.backendv1.facade;

import com.odds_and_ends.backendv1.entity.user.User;
import com.odds_and_ends.backendv1.entity.user.UserRepository;
import com.odds_and_ends.backendv1.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacadeImpl implements UserFacade {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if(name == null) {
            throw new UserNotFoundException();
        }
        return getUserById(Long.valueOf(name));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);
    }
}
