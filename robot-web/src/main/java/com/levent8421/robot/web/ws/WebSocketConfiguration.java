package com.levent8421.robot.web.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Create By leven ont 2020/10/29 21:03
 * Class Name :[WebSocketConfiguration]
 * <p>
 * Config WebSocket
 *
 * @author leven
 */
@Configuration
public class WebSocketConfiguration implements WebSocketConfigurer {
    private final RobotControllerWebSocket robotControllerWebSocket;

    public WebSocketConfiguration(RobotControllerWebSocket robotControllerWebSocket) {
        this.robotControllerWebSocket = robotControllerWebSocket;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(robotControllerWebSocket, "/ws/robot-controller").setAllowedOrigins("*");
    }
}
