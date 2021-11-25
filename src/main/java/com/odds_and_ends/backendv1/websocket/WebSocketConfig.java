package com.odds_and_ends.backendv1.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.odds_and_ends.backendv1.websocket.exception.SocketExceptionListener;
import com.odds_and_ends.backendv1.websocket.security.WebSocketConnectController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WebSocketConfig {

    private final WebSocketAddMappingSupporter mappingSupporter;
    private final WebSocketConnectController connectController;
    private final SocketExceptionListener exceptionListener;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setPort(8081);
        config.setOrigin("*");
        config.setExceptionListener(exceptionListener);
        SocketIOServer server = new SocketIOServer(config);
        mappingSupporter.addListeners(server);
        server.addConnectListener(connectController::onConnect);
        return server;
    }

}