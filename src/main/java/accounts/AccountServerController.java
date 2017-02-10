package accounts;

import base.AccountServer;
import base.AccountServerControllerMBean;

/**
 * Controller account server
 *
 * @author Vladimir Shkerin
 * @since 09.02.2017
 */
public class AccountServerController implements AccountServerControllerMBean {
    private final AccountServer accountServer;

    public AccountServerController(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUsers() {
        return accountServer.getUsersCount();
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int bla) {
        accountServer.setUsersLimit(bla);
    }
}