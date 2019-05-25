package controllers;

import controllers.LoginController.Login;
import models.Destination;
import models.Profile;
import models.Trip;
import models.TripDestination;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repository.DestinationRepository;
import repository.ProfileRepository;
import views.html.createDestinations;
import views.html.destinations;
import views.html.editDestinations;
import views.html.login;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * This class is the controller for the destinations.scala.html file, it provides the route to the
 * destinations page and the method that the page uses.
 */
public class DestinationsController extends Controller {

    private MessagesApi messagesApi;
    private List<Destination> destinationsList = new ArrayList<>();
    private List<Integer> followedDestinationIds = new ArrayList<>();
    private final Form<Destination> form;
    private final Form<Profile> userForm;
    private final Form<Login> loginForm;
    private final DestinationRepository destinationRepository;
    private final ProfileRepository profileRepository;
    private String destShowRoute = "/destinations/show/false";

    /**
     * Constructor for the destination controller class
     *
     * @param formFactory
     * @param messagesApi
     * @param destinationRepository
     * @param profileRepository
     */
    @Inject
    public DestinationsController(FormFactory formFactory, MessagesApi messagesApi, DestinationRepository destinationRepository,
                                  ProfileRepository profileRepository) {
        this.form = formFactory.form(Destination.class);
        this.userForm = formFactory.form(Profile.class);
        this.loginForm = formFactory.form(Login.class);
        this.messagesApi = messagesApi;
        this.destinationRepository = destinationRepository;
        this.profileRepository = profileRepository;
    }

    /**
     * Displays a page showing the destinations to the user
     *
     * @param request
     * @return the list of destinations
     */
    @Security.Authenticated(SecureSession.class)
    public CompletionStage<Result> show(Http.Request request, boolean isPublic) {
        destinationsList.clear();
        Integer userId = SessionController.getCurrentUserId(request);
        return profileRepository.findById(userId).thenApplyAsync(profile -> {
            if (profile.isPresent()) {
                if (isPublic) {
                    ArrayList<Destination> destListTemp = destinationRepository.getPublicDestinations();
                    try {
                        destinationsList = destListTemp;
                    } catch (NoSuchElementException e) {
                        destinationsList = new ArrayList<>();
                    }
                } else {
                    profileRepository.getDestinations(userId).ifPresent(dests -> destinationsList.addAll(dests));
                    destinationRepository.getFollowedDestinations(userId).ifPresent(follows -> destinationsList.addAll(follows));
                }
                destinationRepository.getFollowedDestinationIds(userId).ifPresent(ids -> followedDestinationIds = ids);
                return ok(destinations.render(destinationsList, profile.get(), isPublic, followedDestinationIds, request, messagesApi.preferred(request)));
            } else {
                return redirect("/destinations");
            }
        });
    }

    public CompletionStage<Result> follow(Http.Request request, Integer profileId, int destId, boolean isPublic) {
        Integer profId = SessionController.getCurrentUserId(request);
        return profileRepository.findById(profId).thenApplyAsync(profile -> {
            if (profile.isPresent()) {
                destinationRepository.followDestination(destId, profileId).ifPresent(ids -> followedDestinationIds = ids);
                return ok(destinations.render(destinationsList, profile.get(), isPublic, followedDestinationIds, request, messagesApi.preferred(request)));
            } else {
                return redirect("/destinations");
            }
        });
    }

    public CompletionStage<Result> unfollow(Http.Request request, Integer profileId, int destId, boolean isPublic) {
        Integer profId = SessionController.getCurrentUserId(request);
        return profileRepository.findById(profId).thenApplyAsync(profile -> {
            if (profile.isPresent()) {
                //TODO make it so you can not unfollow destinations inside a trip

                Optional<ArrayList<Integer>> followedTemp = destinationRepository.unfollowDestination(destId, profileId);
                try {
                    followedDestinationIds = followedTemp.get();
                } catch (NoSuchElementException e) {
                    followedDestinationIds = new ArrayList<>();
                }
                if (!isPublic) {
                    Optional<ArrayList<Destination>> destListTemp = profileRepository.getDestinations(profileId);
                    Optional<ArrayList<Destination>> followedListTemp = destinationRepository.getFollowedDestinations(profileId);
                    try {
                        destinationsList = destListTemp.get();
                        destinationsList.addAll(followedListTemp.get());
                    } catch (NoSuchElementException e) {
                        destinationsList = new ArrayList<>();
                    }
                }
                return ok(destinations.render(destinationsList, profile.get(), isPublic, followedDestinationIds, request, messagesApi.preferred(request)));
            } else {
                return redirect("/destinations");
            }
        });
    }

    /**
     * Displays a page to create a destination
     *
     * @param request
     * @return
     */
    @Security.Authenticated(SecureSession.class)
    public Result showCreate(Http.Request request) {
        Destination dest = new Destination();
        dest.setLatitude(0.0);
        dest.setLongitude(0.0);
        Form<Destination> destinationForm = form.fill(dest);
        return ok(createDestinations.render(destinationForm, request, messagesApi.preferred(request)));
    }

    /**
     * This method displays the editDestinations page for the destinations to the user
     *
     * @param request
     * @param id
     * @return
     */
    @Security.Authenticated(SecureSession.class)
    public Result edit(Http.Request request, Integer id) {
        Destination destination = new Destination();
        for (Destination dest : destinationsList) {
            if (dest.getDestinationId() == id) {
                destination = dest;
                break;
            }
        }
        Form<Destination> destinationForm = form.fill(destination);
        return ok(editDestinations.render(id, destination, destinationForm, request, messagesApi.preferred(request)));
    }

    /**
     * This method updates destination in the database
     *
     * @param request
     * @param id      The ID of the destination to editDestinations.
     * @return
     */
    @Security.Authenticated(SecureSession.class)
    public Result update(Http.Request request, Integer id) {
        Integer userId = SessionController.getCurrentUserId(request);
        Form<Destination> destinationForm = form.bindFromRequest(request);
        String visible = destinationForm.field("visible").value().get();
        int visibility = (visible.equals("Public")) ? 1 : 0;
        Destination dest = destinationForm.value().get();
        dest.setVisible(visibility);
        if(destinationRepository.checkValidEdit(dest, userId, id)) {
            return redirect("/destinations/" + id +"/edit").flashing("info", "This destination is already registered and unavailable to create");
        }
        if(longLatCheck(dest)){
            destinationRepository.update(dest, id);
            return redirect(destShowRoute);
        } else {
            return redirect("/destinations/" + id +"/edit").flashing("info", "A destinations longitude(-180 to 180) and latitude(90 to -90) must be valid");
        }
    }

    /**
     * Adds a new destination to the database
     *
     * @param request
     * @return
     */
    @Security.Authenticated(SecureSession.class)
    public Result saveDestination(Http.Request request) {
        Integer userId = SessionController.getCurrentUserId(request);
        if (userId == null) {
            return ok(login.render(loginForm, userForm, request, messagesApi.preferred(request)));
        }
        Form<Destination> destinationForm = form.bindFromRequest(request);
        String visible = destinationForm.field("visible").value().get();
        int visibility = (visible.equals("Public")) ? 1 : 0;
        Destination destination = destinationForm.value().get();
        destination.setProfileId(userId);
        destination.setVisible(visibility);
        if(destinationRepository.checkValid(destination, userId)) {
            return redirect("/destinations/create").flashing("info", "This destination is already registered and unavailable to create");

        }
        if(longLatCheck(destination)){
            destinationRepository.insert(destination);
            return redirect(destShowRoute);
        } else {
            return redirect("/destinations/create").flashing("info", "A destinations longitude(-180 to 180) and latitude(90 to -90) must be valid");
        }
    }


    private boolean longLatCheck(Destination destination) {
        if (destination.getLatitude() > 90 || destination.getLatitude() < -90) {
            return false;
        }
        return !(destination.getLongitude() > 180 || destination.getLongitude() < -180);
    }


    /**
     * Deletes a destination in the database
     * @param id ID of the destination to delete
     * @return
     */
    @Security.Authenticated(SecureSession.class)
    public CompletionStage<Result> delete(Http.Request request, Integer id) {
        Integer profileId = SessionController.getCurrentUserId(request);

        return supplyAsync(() -> {
            // Get all trips
            List<Trip> trips = Trip.find.query()
                    .where()
                    .eq("profile_id", profileId)
                    .findList();

            // Iterate through each trip
            // and get it's destinations
            for (Trip trip : trips) {
                List<TripDestination> destinations = TripDestination.find.query()
                        .where()
                        .eq("trip_id", trip.getId())
                        .findList();

                // Iterate over each destination
                for (TripDestination destination : destinations) {
                    // Cannot delete a destination if there is match
                    // Since it in a trip
                    if (destination.getDestinationId() == id) {
                        return redirect("/destinations/show/false").flashing("failure",
                                "Destination cannot be deleted as it is part of a trip");
                    }
                }
            }
            destinationRepository.delete(id);


            return redirect("/destinations/show/false").flashing("success", "Destination Deleted");
        });

    }

}
