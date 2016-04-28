package com.dropbox.pages;

import com.dropbox.user.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Note:  hostname should go in a properties file - each page only needs the path

public class LoginPage extends AbstractPage<LoginPage> {

    @FindBy(css=".login-email")
    WebElement email;

    @FindBy(css="input[name='login_password']")
    WebElement password;

    @FindBy(css=".login-form .login-button")
    WebElement signIn;

    public LoginPage(WebDriver driver) {
        super(driver);
        title = "Dropbox - Sign in";
        url = "https://www.dropbox.com/login";
    }


    public void enterEmail(User user) {
        email.sendKeys(user.getEmail());
    }

    public void enterPassword(User user) {
        password.sendKeys(user.getPassword());
    }

    public void clickLogin() {
        signIn.click();
    }

    public HomePage loginUser(User user) {
        enterEmail(user);
        enterPassword(user);
        clickLogin();

        return new HomePage(driver);
    }


}
