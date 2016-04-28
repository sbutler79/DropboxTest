package com.dropbox.pages;

import com.dropbox.api.ClientApi;
import com.dropbox.core.DbxException;
import com.dropbox.pages.modals.UploadFileModal;
import com.dropbox.test.BaseTest;
import com.dropbox.user.User;
import com.dropbox.user.UserService;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Homepage - upload file tests
 */
public class HomePageUploadTest extends BaseTest {

    User user;

    @BeforeMethod
    public void setup() {
        super.setup();
        user = UserService.getUserByType(UserService.UserType.FREE);

        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .load()
                .loginUser(user)
                .waitForPageLoad();
    }

    @Test
    public void uploadFileTest() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickUploadFile()
                .clickBasicUploader()
                .uploadTestFile()
                .verifyFileExists(UploadFileModal.testFileName);
    }

    @AfterMethod
    public void clean() {
        try {
            ClientApi.deleteFolder(UploadFileModal.testFileName, "/");
        } catch (DbxException e) {
            e.printStackTrace();
        }
        super.tearDown();
    }

}
