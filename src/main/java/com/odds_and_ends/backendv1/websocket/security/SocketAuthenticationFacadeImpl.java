package com.odds_and_ends.backendv1.websocket.security;

import com.corundumstudio.socketio.SocketIOClient;
import com.odds_and_ends.backendv1.entity.user.User;
import com.odds_and_ends.backendv1.entity.user.UserRepository;
import com.odds_and_ends.backendv1.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketAuthenticationFacadeImpl implements SocketAuthenticationFacade {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser(SocketIOClient client) {
        Long id = client.get(AuthenticationProperty.USER_KEY);
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
