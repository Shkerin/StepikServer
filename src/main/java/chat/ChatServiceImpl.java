package chat;

import base.ChatService;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Chat service
 *
 * @author Vladimir Shkerin
 * @since 31.01.2017
 */
public class ChatServiceImpl implements ChatService {

    private Set<ChatWebSocket> webSocketSet;

    public ChatServiceImpl() {
        this.webSocketSet = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        for (ChatWebSocket user : webSocketSet) {
            try {
                user.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void add(ChatWebSocket webSocket) {
        webSocketSet.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket) {
        webSocketSet.remove(webSocket);
    }

}
