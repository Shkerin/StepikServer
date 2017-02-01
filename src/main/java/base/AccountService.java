package base;

import accounts.UserProfile;

/**
 * External interface for account service
 *
 * @author Vladimir Shkerin
 * @since 30.01.2017
 */
public interface AccountService {

    void addNewUser(UserProfile profile);

    void addSession(String id, UserProfile profile);

    UserProfile getUserByLogin(String login);

    UserProfile getUserBySessionId(String sessionId);

    void deleteSession(String sessionId);

}
