package com.dropbox.pages;

import com.dropbox.pages.modals.ConfirmationModal;
import com.dropbox.pages.modals.UploadFilesModal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sbutler on 4/25/16.
 */
public class HomePage extends AbstractPage<HomePage> {

    @FindBy(id = "upload_button")
    private WebElement uploadButton;

    @FindBy(id = "new_folder_button")
    private WebElement newFolder;

    @FindBy(id = "delete_action_button")
    private WebElement delete;

    @FindBy(id = "rename_action_button")
    private WebElement rename;

    // Files and folders
    @FindAll(@FindBy(className = "browse-file"))
    private List<WebElement> files;

    @FindBy(css = ".browse-new-folder input")
    private WebElement newFolderInput;

    @FindBy(css = ".inplaceeditor-form input")
    private WebElement renameFileInput;

    public Header header;

    public HomePage(WebDriver driver) {
        super(driver);
        title = "Home - Dropbox";
        url = "https://www.dropbox.com/login";
        header = new Header(driver);
    }


    public HomePage createNewFolder(String folderName) {
        clickNewFolder();
        inputNewFolderName(folderName);

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".browser-new-folder")));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Folder creation failed.  Stuck 'creating folder'");
        }

        return this;
    }

    public HomePage clickNewFolder() {
        newFolder.click();
        return this;
    }

    public HomePage inputNewFolderName(String folderName) {
        newFolderInput.sendKeys(folderName);
        newFolderInput.sendKeys(Keys.RETURN);

        return this;
    }

    public HomePage verifyFileExists(String fileName) {
        if(fileExists(fileName) > -1) {
            return this;
        }

        Assert.fail("File name : " + fileName + " was not found.");
        return this;
    }

    public HomePage verifyFileAbsent(String fileName) {
        if(fileExists(fileName) == -1) {
            return this;
        }

        Assert.fail("File name : " + fileName + " was found.  Expected it to be deleted.");
        return this;
    }

    public HomePage clickFile(String fileName) {
        int index = fileExists(fileName);
        if(index > -1) {
           files.get(index).click();
            return this;
        }

        Assert.fail("File name : " + fileName + " was not found.");
        return this;
    }

    /**
     * Return file index if exists, -1 otherwise
     * @param searchName
     * @return
     */
    private int fileExists(String searchName) {
        for(int i = 0; i < files.size(); i++) {
            String fileName = files.get(i).getText();
            if(fileName.contains(searchName)) {
                return i;
            }
        }
        return -1;
    }

    public UploadFilesModal clickUploadFile() {
        uploadButton.click();
        return new UploadFilesModal(driver);
    }

    public HomePage clickDeleteAndConfirm() {
        int numItems = files.size();

        clickDelete().confirmAction();

        try {
            new WebDriverWait(driver, 10).until((WebDriver d) -> {
                return files.size() < numItems;
            });
        } catch (Exception e) {
        }


        return this;
    }

    public ConfirmationModal clickDelete() {
        delete.click();
        return new ConfirmationModal(driver);
    }

    public HomePage clickRename(String fileName) {
        clickFile(fileName);
        rename.click();
        return this;
    }

    public HomePage renameFile(String newName) {
        renameFileInput.sendKeys(newName + Keys.RETURN);

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(new WebElement[] {renameFileInput})));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Folder creation failed.  Stuck 'creating folder'");
        }

        return this;
    }

    public HomePage renameFile(String currentName, String newName) {
        clickRename(currentName);
        return renameFile(newName);
    }



}
