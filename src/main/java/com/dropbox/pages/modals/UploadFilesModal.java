package com.dropbox.pages.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sbutler on 4/25/16.
 */
public class UploadFilesModal {

    private WebDriver driver;

    @FindBy(css = "#modal-content #choose-button")
    private WebElement chooseFiles;

    @FindBy(css = "input[type='file']")
    private WebElement inputFile;

    @FindBy(css = "#modal-content .basic-link-start")
    private WebElement basicUploader;

    public UploadFilesModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UploadFileModal clickBasicUploader() {
        basicUploader.click();

        return new UploadFileModal(driver);
    }

}
