package main;

import accounts.AccountService;
import db.DBService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

/**
 * Основной класс
 *
 * @author Vladimir Shkerin
 * @since 22.01.2017
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Initialization data base
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        // Create servlets
        AccountService accountService = new AccountService();

        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        SignUpServlet signUpServlet = new SignUpServlet(accountService, dbService);
        SignInServlet signInServlet = new SignInServlet(accountService, dbService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(signInServlet), "/signin");

        // Initialization server jetty
        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }

}