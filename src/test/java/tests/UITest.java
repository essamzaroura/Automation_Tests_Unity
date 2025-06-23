package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import config.WebDriverSingleton;
import entities.Post;
import entities.Publisher;
import pages.PostPage;
import pages.PublisherPage;
import pages.LoginPage;

public class UITests {
    WebDriver driver;
    private Publisher publisher;
    private PublisherPage publisherPage;
    private Post post;
    private PostPage postPage;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver instance
        driver = WebDriverSingleton.getInstance();

        // Navigate to admin page
        driver.get("http://localhost:3000/admin");

        // Initialize LoginPage
        LoginPage loginPage = new LoginPage(driver);
        // Perform login with admin credentials
        loginPage.login("admin@example.com", "password");

        // Verify login was successful
        if (!loginPage.isLoggedIn()) {
            WebDriverSingleton.quit();
            throw new RuntimeException("Login failed - check credentials or application state");
        }
    }

    @Test
    public void testPostLifecycle() {
        // Initialize page objects
        publisherPage = new PublisherPage();
        postPage = new PostPage();

        // Step 1: Create Publisher
        publisher = publisherPage.createPublisher(
                "TestPublisher_2",
                "TestPublisher_2@hotmail.com");

        // Step 2+3: Create Post associated with the publisher
        post = postPage.createPost("TestPost_2", publisher, "ACTIVE");

        // Step 4+5: Edit Post status and save changes
        postPage.changePostStatus(post, "REMOVED");

        // Step 6: Validate post status was changed to "Remove"
        String actualStatus = postPage.getPostStatus(post);
        Assert.assertEquals(actualStatus, "REMOVED",
                "Post status was not changed correctly. Expected: Remove, but got: " + actualStatus);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            // Delete the created post if it exists
            if (post != null && postPage != null) {
                System.out.println("Cleaning up: Deleting post - " + post.getTitle());
                postPage.deletePost(post);
                System.out.println("✓ Post deleted successfully");
            }
        } catch (Exception e) {
            System.err.println("Warning: Failed to delete entities during cleanup: " + e.getMessage());
            e.printStackTrace();
        } finally {
            WebDriverSingleton.quit();
        }
    }
}