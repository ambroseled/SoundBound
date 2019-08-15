package controllers.steps.Artist;

import controllers.TestApplication;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.Artist;
import models.MusicGenre;
import models.PassportCountry;
import models.Profile;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;

import java.util.*;

import static org.junit.Assert.*;


public class CreateArtistSteps {


    private Map<String, String> artistForm = new HashMap<>();
    private Map<String, String> dupArtistFrom = new HashMap<>();
    private Result dupResult;
    private List<String> ARTIST_GENRES = new ArrayList<>(Arrays.asList("Indie", "Rock"));
    private List<String> ARTIST_COUNTRIES = new ArrayList<>(Arrays.asList("New Zealand", "Papua New Guinea"));
    private List<Integer> ARTIST_ADMINS = new ArrayList<>(Arrays.asList(1, 2));


    @When("^user is at the artist page$")
    public void userIsAtTheArtistPage() throws Throwable {
        Http.RequestBuilder requestDest = Helpers.fakeRequest()
                .method("GET")
                .uri("/artists")
                .session("connected", "1");
        Helpers.route(TestApplication.getApplication(), requestDest);
    }

    @And("^user enters \"([^\"]*)\" for artist name$")
    public void userEntersForArtistName(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        artistForm.put("artistName", arg0);
    }

    @And("^user enters \"([^\"]*)\" for artist genres$")
    public void userEntersForArtistGenres(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        artistForm.put("genreForm", arg0);
    }

    @And("^user enters \"([^\"]*)\" for artist members$")
    public void userEntersForArtistMembers(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        artistForm.put("members", arg0);
    }

    @And("^user enters \"([^\"]*)\" for artist bio$")
    public void userEntersForArtistBio(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        artistForm.put("biography", arg0);
    }

    @And("^user enters \"([^\"]*)\" for artist country$")
    public void userEntersForArtistCountry(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        artistForm.put("countries", arg0);
    }

    @And("^user presses save artist$")
    public void userPressesSaveArtist() throws Throwable {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method("POST")
                .uri("/artists")
                .bodyForm(artistForm)
                .session("connected", "1");

        Helpers.route(TestApplication.getApplication(), request);
    }

    @Then("^the artist is saved in the database$")
    public void theArtistIsSavedInTheDatabase() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        List<Artist> userArtists = TestApplication.getArtistRepository().getAllUserArtists(1);
        if (userArtists.size() > 0) {

            Artist newArtist = userArtists.get(userArtists.size() - 1);
            assertEquals(newArtist.getArtistName(), "King Crimson");

        } else {
            fail();
        }


    }

    @Given("^I am on the artist create page$")
    public void iAmOnTheArtistCreatePage() throws Throwable {
        Http.RequestBuilder requestDest = Helpers.fakeRequest()
                .method("GET")
                .uri("/artists")
                .session("connected", "2");
        Helpers.route(TestApplication.getApplication(), requestDest);
    }

    @And("^I enter \"([^\"]*)\" into the \"([^\"]*)\" form field$")
    public void iEnterIntoTheFormField(String arg0, String arg1) throws Throwable {
        dupArtistFrom.put(arg1, arg0);
    }

    @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" into the \"([^\"]*)\" form field$")
    public void iEnterAndIntoTheFormField(String arg0, String arg1, String arg2) throws Throwable {
        dupArtistFrom.put(arg2, arg0 + "," + arg1);
    }

    @And("^I submit the form$")
    public void iSubmitTheForm() throws Throwable {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method("POST")
                .uri("/artists")
                .bodyForm(dupArtistFrom)
                .session("connected", "2");
        dupResult = Helpers.route(TestApplication.getApplication(), request);
    }

    @And("^There is a flashing sent with id \"([^\"]*)\"$")
    public void thereIsAFlashingSentWithId(String arg0) throws Throwable {
        assertTrue(dupResult.flash().getOptional("error").isPresent());
    }


    @Then("^The artist genre links are saved$")
    public void theArtistGenreLinksAreSaved() throws Throwable {
        Optional<Artist> foundArtist = TestApplication.getArtistRepository().getArtist(8);
        if (foundArtist.isPresent()) {
            Optional<List<MusicGenre>> optional = TestApplication.getGenreRepository().getArtistGenres(foundArtist.get().getArtistId());
            if (optional.isPresent()) {
                for (MusicGenre genre : optional.get()) {
                    if (!ARTIST_GENRES.contains(genre.getGenre())) {
                        fail();
                    }
                }
            } else {
                fail();
            }
        } else {
            fail();
        }
    }

    @Then("^The artist country links are saved$")
    public void theArtistCountryLinksAreSaved() throws Throwable {
        //Note that artistId 2 is an admin
        Optional<Artist> artist =TestApplication.getArtistRepository().getArtist(2);
        if (artist.isPresent()) {
            boolean valid = true;
            for (String country : artist.get().getCountryList()) {
                if (!ARTIST_COUNTRIES.contains(country)) {
                    valid = false;
                }
            }
            assertTrue(valid);
        } else {
            fail("Countries not found");
        }
    }

    @Then("^The artist admin links are saved$")
    public void theArtistAdminLinksAreSaved() throws Throwable {
        Optional<Artist> artist =TestApplication.getArtistRepository().getArtist(10);
        if (artist.isPresent()) {
            boolean valid = true;
            for (Profile admin : artist.get().getAdminsList()) {
//                System.out.println("AAAA " + admin.getFirstName());
                if (!ARTIST_ADMINS.contains(admin.getProfileId())) {
                    valid = false;
                }
            }
            assertTrue(valid);
        } else {
            fail("Admins not found");
        }
    }
}




