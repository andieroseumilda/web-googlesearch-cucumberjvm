import java.util.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.openqa.selenium.WebDriver;

public class GoogleSearchTests {

    Browser browser;
    GoogleSearch google;
    private WebDriver driver = null;
    private List<String> results;

    @Before
    public void startGoogleSearch() {
        this.browser = new Browser();
        this.driver = this.browser.open();
        this.google = new GoogleSearch(this.driver);
    }

    @After
    public void closeGoogleSearch() {
        this.driver.close();
        this.driver.quit();
    }


    @When("^a Googler searches for the '(.*?)' keyword$")
    public void googler_searches_for_keyword(String keyword) {
        google.open();
        assertThat(driver.getTitle(), is("Google"));
        results = google.search(keyword);
    }

    @Then("^the OneSoftwareTester Wordpress blog link can be found in the first page of Google's search results$")
    public void desired_link_is_found_on_first_page_results() {
        int count = 0;
        for (String result : results) { if (result.contains("onesoftwaretester.wordpress.com")) count++; }
        assertThat(count, greaterThan(0));
    }
}