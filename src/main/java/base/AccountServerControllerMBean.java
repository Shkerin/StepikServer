package base;

/**
 * Bean for account server
 *
 * @author Vladimir Shkerin
 * @since 09.02.2017
 */
@SuppressWarnings("UnusedDeclaration")
public interface AccountServerControllerMBean {

    int getUsers();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);

}
