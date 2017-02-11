package servlets;

import accountServer.AccountServerImpl;
import base.AccountServer;
import context.Context;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for account management
 *
 * @author Vladimir Shkerin
 * @since 09.02.2017
 */
public class AccountServerServlet extends HttpServlet {

    public static final String PAGE_URL = "/home";
    private AccountServer accountServer;

    public AccountServerServlet(Context context) {
        this.accountServer = (AccountServer) context.get(AccountServerImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(accountServer.getUsersLimit());
    }
}
