package main;

import accountServer.ResourceServerController;
import accountServer.ResourceServerControllerMBean;
import context.Context;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.ResourceServerImpl;
import resources.TestResource;
import servlets.ResourceServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Основной класс
 *
 * @author Vladimir Shkerin
 * @since 22.01.2017
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Initialization context
        Context context = Context.getInstance();
//        context.add(DBServiceImpl.class, new DBServiceImpl());
//        context.add(AccountServiceImpl.class, new AccountServiceImpl());
//        context.add(ChatServiceImpl.class, new ChatServiceImpl());
//        context.add(AccountServerImpl.class, new AccountServerImpl(10));
        context.add(ResourceServerImpl.class, new ResourceServerImpl(new TestResource("Vlad", 30)));

        // Initialization data base
//        ((DBService) context.get(DBServiceImpl.class)).printConnectInfo();

//        // Initialization bean controller
//        AccountServerControllerMBean serverStatistics = new AccountServerController(context);
//        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
//        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
//        mbs.registerMBean(serverStatistics, name);

        // Initialization bean controller
        ResourceServerControllerMBean serverStatistics = new ResourceServerController(context);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(serverStatistics, name);

        // Create servlets
//        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
//        SignUpServlet signUpServlet = new SignUpServlet(context);
//        SignInServlet signInServlet = new SignInServlet(context);
//        WebSocketChatServlet chatServlet = new WebSocketChatServlet(context);
//        AccountServerServlet accountServerServlet = new AccountServerServlet(context);
        ResourceServlet resourceServlet = new ResourceServlet(context);

        // Initialization server
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        contextHandler.addServlet(new ServletHolder(allRequestsServlet), AllRequestsServlet.PAGE_URL);
//        contextHandler.addServlet(new ServletHolder(signUpServlet), SignUpServlet.PAGE_URL);
//        contextHandler.addServlet(new ServletHolder(signInServlet), SignInServlet.PAGE_URL);
//        contextHandler.addServlet(new ServletHolder(chatServlet), WebSocketChatServlet.PAGE_URL);
//        contextHandler.addServlet(new ServletHolder(accountServerServlet), AccountServerServlet.PAGE_URL);
        contextHandler.addServlet(new ServletHolder(resourceServlet), ResourceServlet.PAGE_URL);

//        Server server = new Server(5050);
        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }

}
