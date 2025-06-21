package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locators {
        // ============= COMMON ELEMENTS (Used across multiple pages) =============
        public static final By SAVE_BTN = By.id("save-btn");
        public static final By CANCEL_BTN = By.id("cancel-btn");

        // ============= Login ELEMENTS =============
        public static final By EMAIL_FIELD = By.xpath("//input[@name='email']");
        public static final By PASSWORD_FIELD = By.xpath("//input[@name='password']");
        public static final By LOGIN_BUTTON = By.xpath("//button[text()='Login']");

        // ============= MAIN NAVIGATION ELEMENTS =============
        public static final By HAPPY_FOLDER_TAB = By
                        .xpath("//div[contains(@class,'sc-iCmkLe') and contains(text(),'Happy Folder')]");
        public static final By PUBLISHERS_MENU = By.xpath("//a[contains(@href,'/admin/resources/Publisher')]");
        public static final By POSTS_MENU = By.xpath("//a[contains(@href,'/admin/resources/Post')]");
        public static final By PROFILES_MENU = By.xpath("//a[contains(@href,'/admin/resources/Profile')]");

        // ============= PUBLISHER PAGE SPECIFIC ELEMENTS =============
        public static final By NEW_PUBLISHER_BUTTON = By.xpath("//a[@data-testid='action-new']");
        public static final By PUBLISHER_NAME_FIELD = By.id("name");
        public static final By PUBLISHER_EMAIL_FIELD = By.id("email");
        public static final By PUBLISHER_SAVE_BUTTON = By
                        .xpath("//button[@type='submit' and @data-testid='button-save']");

        // ============= POST PAGE SPECIFIC ELEMENTS =============
        public static final By NEW_POST_BUTTON = By.xpath("//a[@data-testid='action-new']");
        public static final By POST_TITLE_FIELD = By.id("title");
        public static final By STATUS_ACTIVE_OPTION = By.xpath("//div[text()='ACTIVE']");
        public static final By STATUS_REMOVED_OPTION = By.xpath("//div[text()='REMOVED']");
        public static final String POST_CONTAINER = "//tr[.//section[normalize-space(text())='%s']]";
        public static final String POST_TITLE_IN_ROW_CLICKABLE = POST_CONTAINER
                        + "//section[@data-css='Post-list-title'][@data-testid='property-list-title']";
        public static final String POST_TITLE_DIRECT = "//section[@data-css='Post-list-title'][@data-testid='property-list-title'][normalize-space(text())='%s']";
        public static final String STATUS_OPTION_TEMPLATE = "//div[text()='%s']";
        public static final By POST_STATUS_DROPDOWN = By
                        .xpath("//input[contains(@id, 'react-select-') and contains(@id, '-input')][@role='combobox']");

        public static final String STATUS_OPTION = "//div[@id='react-select-5-listbox']//div[contains(@class, 'css-gjT6A') and text()='%s']";
        public static final String POST_STATUS_DISPLAY = "//div[@data-post='%s']//span[@class='status']";
        public static final By POST_PUBLISHER_DROPDOWN = By.id("react-select-6-input");
        public static final String POST_PUBLISHER_OPTION_TEMPLATE = "//div[@id='react-select-6-listbox']//div[contains(@class, 'option') and .//text()[normalize-space(.)='%s']]";
        public static final By POST_SAVE_BUTTON = By.xpath("//button[@type='submit']");
        public static final By POST_EDIT_BUTTON = By
                        .xpath("//a[@data-testid='action-edit'][@data-css='Post-edit-button']");
        public static final By POST_DELETE_BUTTON = By.xpath("//a[@data-testid='action-delete']");
        public static final By CONFIRM_DELETE_BUTTON = By.cssSelector("button[label='Confirm']");

        public static final String POST_STATUS_IN_LIST = "//tbody[.//section[normalize-space(text())='%s']]//td[@data-property-name='status']";

        // ============= PROFILE PAGE SPECIFIC ELEMENTS =============
        public static final By NEW_PROFILE_BTN = By.id("new-profile");
        public static final By PROFILE_NAME_FIELD = By.id("profile-name");
        public static final By PROFILE_TYPE_DROPDOWN = By.id("profile-type");

}