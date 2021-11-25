package com.odds_and_ends.backendv1.websocket.security;

import com.corundumstudio.socketio.SocketIOClient;
import com.odds_and_ends.backendv1.entity.user.User;

public interface SocketAuthenticationFacade {
    User getCurrentUser(SocketIOClient client);
}
