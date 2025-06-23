package entities;

public class Post {
    private String id;
    private String title;
    private Publisher publisher;
    private String status;

    public Post(String title, String status, Publisher publisher) {
        if (publisher == null)
            throw new IllegalArgumentException("Post requires Publisher");
        this.title = title;
        this.status = status;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}