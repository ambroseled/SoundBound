package controllers;

import models.Destination;
import models.Profile;
import play.Application;
import play.Mode;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import repository.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProvideApplication extends WithApplication {

    protected ProfileRepository profileRepository;
    protected DestinationRepository destinationRepository;
    protected TripRepository tripRepository;
    protected RolesRepository rolesRepository;
    protected PhotoRepository photoRepository;
    protected TreasureHuntRepository treasureHuntRepository;
    protected UndoStackRepository undoStackRepository;

    @Override
    public Application provideApplication() {
        return new GuiceApplicationBuilder().in(Mode.TEST).build();
    }


    protected Integer loginUser() {
        Map<String, String> formData = new HashMap<>();
        formData.put("email", "john@gmail.com");
        formData.put("password", "password");

        Http.RequestBuilder request = Helpers.fakeRequest()
                .method("POST")
                .uri("/login")
                .bodyForm(formData);

        Result result = Helpers.route(provideApplication(), request);

        for (Profile profile : Profile.find.all()) {
            if (profile.getEmail().equals("john@gmail.com")) {
                return profile.getProfileId();
            }
        }
        return 0;
    }


    protected Integer adminLogin() {
        Map<String, String> formData = new HashMap<>();
        formData.put("email", "bob@gmail.com");
        formData.put("password", "password");

        Http.RequestBuilder request = Helpers.fakeRequest()
                .method("POST")
                .uri("/login")
                .bodyForm(formData);

        Result result = Helpers.route(provideApplication(), request);

        for (Profile profile : Profile.find.all()) {
            if (profile.getEmail().equals("bob@gmail.com")) {
                return profile.getProfileId();
            }
        }
        return 0;
    }

    protected void injectRepositories() {
        app = provideApplication();
        app.injector().instanceOf(PhotoRepository.class);
        app.injector().instanceOf(TripDestinationsRepository.class);
        app.injector().instanceOf(TripRepository.class);
        app.injector().instanceOf(NationalityRepository.class);
        app.injector().instanceOf(PassportCountryRepository.class);
        app.injector().instanceOf(RolesRepository.class);
        app.injector().instanceOf(UndoStackRepository.class);

        treasureHuntRepository = app.injector().instanceOf(TreasureHuntRepository.class);
        rolesRepository = app.injector().instanceOf(RolesRepository.class);
        tripRepository = app.injector().instanceOf(TripRepository.class);
        profileRepository = app.injector().instanceOf(ProfileRepository.class);
        destinationRepository = app.injector().instanceOf(DestinationRepository.class);
        photoRepository = app.injector().instanceOf(PhotoRepository.class);
        undoStackRepository = app.injector().instanceOf(UndoStackRepository.class);
    }

    protected List<Destination> getUserDest(int id) {
        return destinationRepository.getUserDestinations(id);
    }


    /**
     * Method to test a photo upload from the personal photos directory
     * @return tempFile of the locally stored image. Play uses tempFiles by default
     * @throws IOException if the image file does not exist
     */
    public File getPersonalPhoto() throws IOException {
        String filePath = System.getProperty("user.dir") + "/public/images/" + "testPic1.jpg";
        java.nio.file.Path tempFilePath = Files.createTempFile(null, null);
        byte[] expectedData = filePath.getBytes();
        Files.write(tempFilePath, expectedData);

        return tempFilePath.toFile();
    }
}
