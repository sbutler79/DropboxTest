package com.dropbox.pages.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * General modal used for action confirmation
 */
public class ConfirmationModal {

    private WebDriver driver;

    @FindBy(css = "#react-modal-root .db-modal-content .button-primary.dbmodal-button")
    private WebElement confirmAction;

    @FindBy(css = "#react-modal-root .db-modal-content .button-tertiary.dbmodal-button")
    private WebElement cancelActione;

    public ConfirmationModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void confirmAction() {
        confirmAction.click();

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".db-modal-box")));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("File deletion failed.");
        }
    }




}
