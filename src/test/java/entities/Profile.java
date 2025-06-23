package entities;

public class Profile {
    private String name;
    private Publisher publisher;

    public Profile(String name, Publisher publisher) {
        if (publisher == null)
            throw new IllegalArgumentException("Profile requires Publisher");
        this.name = name;
        this.publisher = publisher;
    }
}