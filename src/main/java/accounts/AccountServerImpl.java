package accounts;

import base.AccountServer;

/**
 * Server implementation for account management
 *
 * @author Vladimir Shkerin
 * @since 09.02.2017
 */
public class AccountServerImpl implements AccountServer {

    private int userCount;
    private int userLimit;

    public AccountServerImpl(int userLimit) {
        this.userCount = 0;
        this.userLimit = userLimit;
    }

    @Override
    public void addUser() {
        userCount += 1;
    }

    @Override
    public void removeUser() {
        userCount -= 1;
    }

    @Override
    public int getUsersLimit() {
        return userLimit;
    }

    @Override
    public void setUsersLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    @Override
    public int getUsersCount() {
        return userCount;
    }

}
