package pages;

import pages.BasePage;
import config.Locators;
import entities.Profile;
import entities.Publisher;

public class ProfilePage extends BasePage {
    public Profile createProfile(String name, Publisher publisher) {
        click(Locators.HAPPY_FOLDER_TAB);
        click(Locators.PROFILES_MENU);
        click(Locators.NEW_PROFILE_BTN);
        type(Locators.PROFILE_NAME_FIELD, name);
        click(Locators.SAVE_BTN);
        return new Profile(name, publisher);
    }
}