package com.mycompany.lab4;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.websocket.Session;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Named(value = "sessionManager")
@ApplicationScoped
public class WebSocketSessionManager {

    private final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public void send(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
            }
        }
    }
}
