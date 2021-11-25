package com.odds_and_ends.backendv1.websocket.security;

import com.corundumstudio.socketio.SocketIOClient;
import com.odds_and_ends.backendv1.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WebSocketConnectController {

    private final JwtTokenProvider validator;

    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("Authorization");
        Authentication authentication = validator.authenticateUser(token);
        client.set(AuthenticationProperty.USER_KEY, authentication.getName());
    }
}
