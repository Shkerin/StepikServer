package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * L1.1 Сервлет проверки авторизации
 *
 * При получении POST запроса на signin, после регистрации, SignInServlet проверяет,
 * логин/пароль пользователя. Если пользователь уже зарегистрирован, север отвечает:
 *
 * Status code (200)
 * и текст страницы:
 * Authorized: login
 *
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

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

//        if (login == null || pass == null) {
        if (login == null) {
            response.getWriter().print("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            UserProfile profile = accountService.getUserByLogin(login);
            if (profile == null) {
                response.getWriter().print("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                response.getWriter().print("Authorized: " + profile.getLogin());
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }

    }

}
