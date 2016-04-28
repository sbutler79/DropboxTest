package com.dropbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Header - notification feed, account dropdown, etc.
 */
public class Header {

    private WebDriver driver;

    public static final String logoutLocator = "a[href='/logout']";

    @FindBy(css= "#header-account-menu button")
    WebElement accountDropdown;

    @FindBy(css = logoutLocator)
    WebElement logout;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAccountDropdown() {
        accountDropdown.click();
    }

    public void clickLogout() {
        logout.click();
    }

    public LoginPage logoutUser() {
        clickAccountDropdown();
        clickLogout();

        return new LoginPage(driver);
    }


}
