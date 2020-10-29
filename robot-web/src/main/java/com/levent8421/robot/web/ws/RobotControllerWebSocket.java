package com.levent8421.robot.web.ws;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Create By Levent8421
 * Create Time: 2020/10/29 18:28
 * Class Name: RobotControllerWebSocket
 * Author: Levent8421
 * Description:
 * 机器人控制器 WebSocket
 *
 * @author Levent8421
 */
@Slf4j
@ServerEndpoint("/ws/robot-controller")
@Component
public class RobotControllerWebSocket extends TextWebSocketHandler {
    private WebSocketSession session;
    private final RobotControlMessageProcessor robotControlMessageProcessor;

    public RobotControllerWebSocket(RobotControlMessageProcessor robotControlMessageProcessor) {
        this.robotControlMessageProcessor = robotControlMessageProcessor;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        tryCloseSession();
        this.session = session;
        log.debug("Session opened : [{}]", session.getId());
    }

    private void tryCloseSession() {
        if (session != null) {
            try {
                session.close();
                log.debug("Session closed : [{}]", session.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        session = null;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        tryCloseSession();
        log.debug("On session close: [{}]", session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.warn("On webSocket error : [{}]", session.getId(), exception);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        final String text = message.getPayload();
        log.debug("message [{}] from session: [{}]", text, session.getId());
        final RobotControlMessage controlMessage = JSON.parseObject(text, RobotControlMessage.class);
        robotControlMessageProcessor.process(controlMessage);
    }
}
