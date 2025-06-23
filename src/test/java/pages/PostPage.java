package pages;

import pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import config.Locators;
import entities.Post;
import entities.Publisher;

public class PostPage extends BasePage {

    public Post createPost(String title, Publisher publisher, String post_status) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.POSTS_MENU)).click();
        } catch (org.openqa.selenium.TimeoutException e) {
            click(Locators.HAPPY_FOLDER_TAB);
            wait.until(ExpectedConditions.elementToBeClickable(Locators.POSTS_MENU)).click();
        }
        click(Locators.NEW_POST_BUTTON);
        type(Locators.POST_TITLE_FIELD, title);
        selectPostStatus(post_status);
        selectPublisher(publisher.getEmail());
        click(Locators.POST_SAVE_BUTTON);
        return new Post(title, "ACTIVE", publisher);
    }

    private void selectPostStatus(String status) {
        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(Locators.POST_STATUS_DROPDOWN));
        click(statusDropdown);
        By statusOptionLocator = By.xpath(String.format(Locators.STATUS_OPTION_TEMPLATE, status));
        WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(statusOptionLocator));
        click(statusOption);
    }

    private void selectPublisher(String publisherEmail) {
        System.out.println("Searching for publisher: " + publisherEmail);
        WebElement publisherDropdown = wait
                .until(ExpectedConditions.elementToBeClickable(Locators.POST_PUBLISHER_DROPDOWN));
        click(publisherDropdown);
        By publisherOptionLocator = By.xpath(String.format(Locators.POST_PUBLISHER_OPTION_TEMPLATE, publisherEmail));
        WebElement publisherOption = wait.until(ExpectedConditions.visibilityOfElementLocated(publisherOptionLocator));
        wait.until(ExpectedConditions.elementToBeClickable(publisherOption));
        click(publisherOption);
    }

    // Update existing post status
    public void changePostStatus(Post post, String status) {
        // 1. First find the specific post container element and click on it
        By postTitleDirectLocator = By.xpath(String.format(Locators.POST_TITLE_DIRECT, post.getTitle()));
        WebElement postTitleElement = wait.until(ExpectedConditions.elementToBeClickable(postTitleDirectLocator));
        click(postTitleElement);

        // 2. click on Edit button
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.POST_EDIT_BUTTON));
        click(editButton);

        // 3. Select the specific status option and Save
        selectPostStatus(status);
        click(Locators.POST_SAVE_BUTTON);

    }

    public String getPostStatus(Post post) {
        // Navigate to Posts list if not already there
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.POSTS_MENU)).click();
        } catch (org.openqa.selenium.TimeoutException e) {
            click(Locators.HAPPY_FOLDER_TAB);
            wait.until(ExpectedConditions.elementToBeClickable(Locators.POSTS_MENU)).click();
        }

        // Find the status cell for the specific post
        By postStatusLocator = By.xpath(String.format(Locators.POST_STATUS_IN_LIST, post.getTitle()));
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(postStatusLocator));
        return statusElement.getText().trim();
    }

    public void deletePost(Post post) {
        // 1. Navigate to Posts list if not already there
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.POSTS_MENU)).click();
        } catch (org.openqa.selenium.TimeoutException e) {
            click(Locators.HAPPY_FOLDER_TAB);
            wait.until(ExpectedConditions.elementToBeClickable(Locators.POSTS_MENU)).click();
        }

        // 2. Find the specific post and click on its title to select it
        By postTitleDirectLocator = By.xpath(String.format(Locators.POST_TITLE_DIRECT, post.getTitle()));
        WebElement postTitleElement = wait.until(ExpectedConditions.elementToBeClickable(postTitleDirectLocator));
        click(postTitleElement);

        // 3. Click on Delete button
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.POST_DELETE_BUTTON));
        click(deleteButton);

        // 4. Wait for confirmation dialog and click "Confirm" button
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.CONFIRM_DELETE_BUTTON));
        click(confirmButton);
    }

}