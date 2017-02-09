package base;

/**
 * Server for manage accounts
 *
 * @author Vladimir Shkerin
 * @since 09.02.2017
 */
public interface AccountServer {

    void addUser();

    void removeUser();

    int getUserLimit();

    void setUserLimit(int userLimit);

    int getUserCount();

}
