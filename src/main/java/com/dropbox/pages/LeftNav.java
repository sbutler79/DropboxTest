package com.dropbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Header - notification feed, account dropdown, etc.
 */
public class LeftNav {

    private WebDriver driver;

    @FindBy(css= "#header-account-menu button")
    WebElement accountDropdown;

    @FindBy(css = "a[href='/logout']")
    WebElement logout;

    public LeftNav(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAccountDropdown() {
        accountDropdown.click();
    }

    public void clickLogout() {
        logout.click();
    }

    public void logoutUser() {
        clickAccountDropdown();
        clickLogout();
    }


}
