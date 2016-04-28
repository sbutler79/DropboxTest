package com.dropbox.pages.modals;

import com.dropbox.pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;

/**
 * Modal for uploading a single file
 */
public class UploadFileModal {

    private WebDriver driver;

    public static final String testFileName = "test.txt";

    @FindBy(css = "#basic-choose-button")
    private WebElement chooseFile;

    @FindBy(css = "#basic-upload-modal input[type=\"file\"]")
    private WebElement inputFile;

    public UploadFileModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage uploadTestFile() {

        // Get test file path
        File file = new File("src/test/resources/files/" + testFileName);
        String absolutePath = file.getAbsolutePath();

        inputFile.sendKeys(absolutePath);

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#modal-box")));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("File upload failed.");
        }

        return new HomePage(driver);
    }




}
