package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import db.DBException;
import db.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * L1.1 Сервлет авторизации
 * <p>
 * При получении POST запроса на signup сервлет SignUpServlet должн запомнить логин и пароль в AccountService.
 * После этого польователь с таким логином считается зарегистрированным.
 *
 * @author Vladimir Shkerin
 * @since 27.01.2017
 */
public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;
    private final DBService dbService;

    public SignUpServlet(AccountService accountService, DBService dbService) {
        this.accountService = accountService;
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                long id = dbService.setUser(login, pass);
                System.out.println("Create user \"" + login + "\" by id = " + id);
            } catch (DBException e) {
                e.printStackTrace();
            }

            UserProfile profile = new UserProfile(login, pass);
            accountService.addNewUser(profile);
            accountService.addSession(request.getSession().getId(), profile);
        }

    }

}
