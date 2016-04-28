package com.dropbox.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Extend pages from this class
 */
public abstract class AbstractPage<T> {

    protected String url;
    protected String title;
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public T load() {
        driver.get(url);

        return (T) this;
    }

    public boolean isLoggedIn() {
        try {
            driver.findElement(By.cssSelector(Header.logoutLocator));
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public String getTitle() {
        return title;
    }

    public T verifyPageTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, title, "Page title not correct");

        return (T) this;
    }

    public T waitForPageLoad() {
        try {
            new WebDriverWait(driver, 30).until((WebDriver d) -> {
                return driver.getTitle().equals(title);
            });
        } catch (Exception e) {
            Assert.fail("Page did not load!");
        }
        return (T) this;
    }

}
