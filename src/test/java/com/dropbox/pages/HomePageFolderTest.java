package com.dropbox.pages;

import com.dropbox.api.ClientApi;
import com.dropbox.core.DbxException;
import com.dropbox.test.BaseTest;
import com.dropbox.user.User;
import com.dropbox.user.UserService;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Folder tests
 */
public class HomePageFolderTest extends BaseTest {

    public final static String folderNameRoot = "testFolder";
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
    public void createFolderTest() {
        Random rand = new Random();
        int num = rand.nextInt(1000000);
        String folderName = folderNameRoot + num;

        HomePage homePage = new HomePage(driver);
        homePage
                .load()
                .createNewFolder(folderName)
                .verifyFileExists(folderName);
    }

    @Test
    public void deleteFolderTest() {
        Random rand = new Random();
        int num = rand.nextInt(1000000);
        String folderName = folderNameRoot + num;

        try {
            ClientApi.createFolder(folderName, "/");
        } catch (DbxException e) {
            Assert.fail("Folder creation via api failed");
        }

        HomePage homePage = new HomePage(driver);
        homePage
                .load()
                .clickFile(folderName)
                .clickDeleteAndConfirm()
                .verifyFileAbsent(folderName);
    }

    @Test
    public void renameFolderTest() {
        Random rand = new Random();
        int num = rand.nextInt(1000000);
        String folderNameOriginal = folderNameRoot + num;

        num = rand.nextInt(1000000);
        String folderName = folderNameRoot + num;

        try {
            ClientApi.createFolder(folderNameOriginal, "/");
        } catch (DbxException e) {
            Assert.fail("Folder creation via api failed");
        }

        HomePage homePage = new HomePage(driver);
        homePage
                .load()
                .renameFile(folderNameOriginal, folderName)
                .verifyFileExists(folderName);
    }

    @AfterMethod
    public void clean() {
        try {
            ClientApi.deleteFoldersContaining(folderNameRoot);
        } catch (DbxException e) {
            e.printStackTrace();
        }
        super.tearDown();
    }

}
