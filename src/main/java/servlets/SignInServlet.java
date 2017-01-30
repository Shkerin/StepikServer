package servlets;

import accounts.AccountService;
import db.DBException;
import db.DBService;
import db.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * L1.1 Сервлет проверки авторизации
 * <p>
 * При получении POST запроса на signin, после регистрации, SignInServlet проверяет,
 * логин/пароль пользователя. Если пользователь уже зарегистрирован, север отвечает:
 * <p>
 * Status code (200)
 * и текст страницы:
 * Authorized: login
 * <p>
 * если нет:
 * Status code (401)
 * текст страницы:
 * Unauthorized
 *
 * @author Vladimir Shkerin
 * @since 27.01.2017
 */
public class SignInServlet extends HttpServlet {

    private final AccountService accountService;
    private final DBService dbService;

    public SignInServlet(AccountService accountService, DBService dbService) {
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
            response.getWriter().print("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            try {
                UsersDataSet usersDataSet = dbService.getUser(login, pass);
                response.getWriter().print("Authorized: " + usersDataSet.getName());
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (DBException e) {
                response.getWriter().print("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

    }

}
