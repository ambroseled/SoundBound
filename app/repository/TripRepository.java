package repository;

import com.google.common.collect.TreeMultimap;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Model;
import io.ebean.SqlRow;
import models.Destination;
import models.Profile;
import models.Trip;
import models.TripDestination;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * A trip repository that executes database operations in a different
 * execution context handles all interactions with the trip table .
 */
public class TripRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;
    private final TripDestinationsRepository tripDestinationsRepository;
    private final DestinationRepository destinationRepository;
    private final ProfileRepository profileRepository;

    @Inject
    public TripRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext, TripDestinationsRepository tripDestinationsRepository, ProfileRepository profileRepository, DestinationRepository destinationRepository) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
        this.tripDestinationsRepository = tripDestinationsRepository;
        this.destinationRepository = destinationRepository;
        this.profileRepository = profileRepository;
    }


    /**
     * insert trip into database
     * @param trip
     * @param tripDestinations
     */
    public void insert(Trip trip, ArrayList<TripDestination> tripDestinations) {
        ebeanServer.insert(trip);
        for (TripDestination tripDestination : tripDestinations) {
                tripDestination.setTripId(trip.getId());
                Destination dest = destinationRepository.lookup(tripDestination.getDestinationId());
                if (dest.getVisible() == 1) {
                   //TODO Swap to ownership of global admin
                }
            ebeanServer.insert(tripDestination);
        }
    }


    /**
     * Removes a trip from the database
     * @param tripID the id of the trip to remove
     * @return the completionStage
     */
    public CompletionStage<Optional<Integer>> delete(int tripID) {
        return supplyAsync(() -> {
            try {
                final Optional<Trip> tripOptional = Optional.ofNullable(ebeanServer.find(Trip.class).setId(tripID).findOne());
                tripOptional.ifPresent(Model::delete);
                return tripOptional.map(p -> p.getId());
            } catch (Exception e) {
                return Optional.empty();
            }
        }, executionContext);
    }

    /**
     * Soft deletes a trip (indicates the trip will be deleted)
     *
     * @param tripId The ID of the trip to soft delete
     * @return
     */
    public CompletionStage<Optional<Integer>> softDelete(int tripId) {
        return supplyAsync(() -> {
            try {
                Trip targetTrip = ebeanServer.find(Trip.class).setId(tripId).findOne();
                if (targetTrip != null) {
                    targetTrip.setSetSoftDelete(1);
                    targetTrip.update();
                    return Optional.of(targetTrip.getId());
                } else {
                    return Optional.empty();
                }
            } catch(Exception e) {
                return Optional.empty();
            }
        }, executionContext);
    }

    /**
     * Takes in a user and sets ups the users trips from the database
     * @param currentUser User that gets trips set
     * @return currentUser user after trips have been set
     */
    public Profile setUserTrips(Profile currentUser) {
        TreeMultimap<Long, Integer> trips = TreeMultimap.create();
        TreeMap <Integer, Trip> tripMap = new TreeMap<>();
        // Getting the trips out of the database
        List<Trip> result = Trip.find.query().where()
                .eq("profile_id", currentUser.getProfileId())
                .eq("soft_delete",0)
                .findList();

        for (Trip trip : result) {
            ArrayList<TripDestination> tripDestinations = new ArrayList<>();
            // Getting the tripDestinations out of the database for each trip returned
            List<TripDestination> tripDests = TripDestination.find.query()
                    .where()
                    .eq("trip_id", trip.getId())
                    .findList();
            for (TripDestination tripDest : tripDests) {
                // Getting the destinations for each tripDestination
                List<Destination> destinations = Destination.find.query()
                        .where()
                        .eq("destination_id", tripDest.getDestinationId())
                        .eq("soft_delete",0)
                        .findList();
                tripDest.setDestination(destinations.get(0));
                tripDestinations.add(tripDest);
            }
            trip.setDestinations(tripDestinations);
            trips.put(trip.getFirstDate(), trip.getId());
            tripMap.put(trip.getId(), trip);
        }
        // Returning the trips found
        currentUser.setTrips(trips);
        currentUser.setTripMaps(tripMap);
        return currentUser;
    }



    /**
     * code to return trip from id
     * @param tripId
     * @return
     */
    public Trip getTrip(int tripId) {
        // Getting the trips out of the database
        List<Trip> result = Trip.find.query().where()
                .eq("trip_id", tripId)
                .eq("soft_delete",0)
                .findList();

        Trip trip = result.get(0);

        ArrayList<TripDestination> tripDestinations = new ArrayList<>();
        TreeMap<Integer, TripDestination> orderedDestiantions = new TreeMap<>();
        List<TripDestination> tripDests = TripDestination.find.query()
                .where()
                .eq("trip_id", tripId)
                .findList();

        for (TripDestination tripDest : tripDests) {
            // Getting the destinations for each tripDestination
            List<Destination> destinations = Destination.find.query()
                    .where()
                    .eq("destination_id", tripDest.getDestinationId())
                    .findList();
            tripDest.setDestination(destinations.get(0));
            orderedDestiantions.put(tripDest.getDestOrder(), tripDest);
        }
        trip.setOrderedDestiantions(orderedDestiantions);
        return trip;
    }

    /**
     * Method to get all trips
     *
     * @return List of all trips
     */
    public List<Trip> getAll() {
        String selectQuery = "SELECT * FROM trip WHERE soft_delete = 0;";
        List<SqlRow> rows = ebeanServer.createSqlQuery(selectQuery).findList();
        List<Trip> allTrips = new ArrayList<>();
        for (SqlRow row : rows) {
            allTrips.add(getTrip(row.getInteger("trip_id")));

        }
        return allTrips;
    }

}
