package accounts;

import base.AccountService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class AccountServiceImpl
 *
 * @author Vladimir Shkerin
 * @since 01.02.2017
 */
public class AccountServiceImplTest {

    private final static String TEST_LOGIN = "TEST_LOGIN";
    private final static String TEST_PASS = "TEST_PASS";
    private final static String TEST_SESSION = "sdf39sde";

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private final UserProfile userProfile;

    private final AccountService accountService;

    public AccountServiceImplTest() {
        this.loginToProfile = new HashMap<>();
        this.sessionIdToProfile = new HashMap<>();
        this.userProfile = new UserProfile(TEST_LOGIN, TEST_PASS);
        this.accountService = new AccountServiceImpl();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        loginToProfile.put(TEST_LOGIN, userProfile);
        sessionIdToProfile.put(TEST_SESSION, userProfile);
        accountService.addNewUser(userProfile);
        accountService.addSession(TEST_SESSION, userProfile);
    }

    @Test
    public void testAddNewUser() throws Exception {
        Assert.assertEquals(accountService.getUserByLogin(TEST_LOGIN), userProfile);
    }

    @Test
    public void testAddSession() throws Exception {
        Assert.assertEquals(accountService.getUserBySessionId(TEST_SESSION), userProfile);
    }

    @Test
    public void testDeleteSession() throws Exception {
        accountService.deleteSession(TEST_SESSION);
        Assert.assertNotEquals(accountService.getUserBySessionId(TEST_SESSION), userProfile);
    }

}