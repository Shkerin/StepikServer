package base;

import chat.ChatWebSocket;

/**
 * External interface for chat service
 *
 * @author Vladimir Shkerin
 * @since 31.01.2017
 */
public interface ChatService {

    void add(ChatWebSocket webSocket);

    void sendMessage(String data);

    void remove(ChatWebSocket webSocket);

}
