package pages;

import pages.BasePage;
import config.Locators;
import entities.Publisher;

public class PublisherPage extends BasePage {
    public Publisher createPublisher(String name, String email) {
        // Navigate from landing page
        click(Locators.HAPPY_FOLDER_TAB);
        click(Locators.PUBLISHERS_MENU);
        click(Locators.NEW_PUBLISHER_BUTTON);

        // Fill required fields
        type(Locators.PUBLISHER_NAME_FIELD, name);
        type(Locators.PUBLISHER_EMAIL_FIELD, email);
        // sleep(4);
        click(Locators.PUBLISHER_SAVE_BUTTON);
        return new Publisher(name, email);
    }
}