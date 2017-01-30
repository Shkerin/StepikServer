package main;

import accounts.AccountServiceImpl;
import base.DBService;
import chat.ChatServiceImpl;
import context.Context;
import db.DBServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.WebSocketChatServlet;

/**
 * Основной класс
 *
 * @author Vladimir Shkerin
 * @since 22.01.2017
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Initialization context
        Context context = new Context();
        context.add(DBServiceImpl.class, new DBServiceImpl());
        context.add(AccountServiceImpl.class, new AccountServiceImpl());
        context.add(ChatServiceImpl.class, new ChatServiceImpl());

        // Initialization data base
        ((DBService) context.get(DBServiceImpl.class)).printConnectInfo();

        // Create servlets
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        SignUpServlet signUpServlet = new SignUpServlet(context);
        SignInServlet signInServlet = new SignInServlet(context);
        WebSocketChatServlet chatServlet = new WebSocketChatServlet(context);

        // Initialization server
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(allRequestsServlet), "/*");
        contextHandler.addServlet(new ServletHolder(signUpServlet), "/signup");
        contextHandler.addServlet(new ServletHolder(signInServlet), "/signin");
        contextHandler.addServlet(new ServletHolder(chatServlet), "/chat");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }

}