package accountServer;

import base.AccountServer;

/**
 * Server implementation for account management
 *
 * @author Vladimir Shkerin
 * @since 09.02.2017
 */
public class AccountServerImpl implements AccountServer {

    private int usersCount;
    private int usersLimit;

    public AccountServerImpl(int usersLimit) {
        this.usersCount = 0;
        this.usersLimit = usersLimit;
    }

    @Override
    public void addUser() {
        usersCount += 1;
    }

    @Override
    public void removeUser() {
        usersCount -= 1;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int userLimit) {
        this.usersLimit = userLimit;
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }

}
