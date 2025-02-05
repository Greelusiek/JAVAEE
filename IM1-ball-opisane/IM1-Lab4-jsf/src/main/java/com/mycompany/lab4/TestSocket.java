package com.mycompany.lab4;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.inject.Inject;
import jakarta.websocket.OnMessage;
import java.io.IOException;

@ServerEndpoint("/testChannel")
public class TestSocket {

    @Inject
    private WebSocketSessionManager sessionManager;

    @OnOpen
    public void onOpen(Session session) {
        sessionManager.addSession(session);
        System.out.println("Connection opened: " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        sessionManager.removeSession(session);
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message received from client: " + message);
        try {
            // Echo the message back to the client
            session.getBasicRemote().sendText("Echo: " + message);
        } catch (IOException e) {
        }
    }    
}
