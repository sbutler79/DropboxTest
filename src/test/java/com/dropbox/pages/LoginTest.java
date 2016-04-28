package com.dropbox.pages;

import com.dropbox.test.BaseTest;
import com.dropbox.user.User;
import com.dropbox.user.UserService;
import org.testng.annotations.Test;


/**
 * Dropbox login page
 */
public class LoginTest extends BaseTest {


    @Test
    public void loginLogoutTest() {

        User user = UserService.getUserByType(UserService.UserType.FREE);

        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .load()
                .loginUser(user)
                .verifyPageTitle()
                .header
                .logoutUser()
                .verifyPageTitle();

    }

}
