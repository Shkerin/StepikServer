package servlets;

import base.ChatService;
import chat.ChatServiceImpl;
import chat.ChatWebSocket;
import context.Context;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

/**
 * Web socket chat servlet
 *
 * @author Vladimir Shkerin
 * @since 31.01.2017
 */
@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {

    public static final String PAGE_URL = "/chat";

    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final ChatService chatService;

    public WebSocketChatServlet(Context context) {
        this.chatService = (ChatService) context.get(ChatServiceImpl.class);
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }

}
