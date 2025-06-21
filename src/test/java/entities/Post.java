package entities;

public class Post {
    private String title;
    private Publisher publisher;
    private boolean published = true; // Default to published

    public Post(String title, Publisher publisher) {
        if (publisher == null)
            throw new IllegalArgumentException("Post requires Publisher");
        this.title = title;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}