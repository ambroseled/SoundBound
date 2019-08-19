package models;

import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Events extends Model {

    @Id
    private int eventId;

    private String eventName;

    private String description;

    private int destinationId;

    private Date startDate;

    private Date endDate;

    private int ageRestriction;

    @Transient
    private List<MusicGenre> eventGenres;

    @Transient
    private List<String> eventTypes;

    @Transient
    private List<Artist> eventArtists;

    @Transient
    private String artistForm;

    @Transient
    private String genreForm;

    @Transient
    private String typeForm;

    @Transient
    private String ageForm;

    /**
     * Traditional constructor for events used when retrieving an Event from the data base
     * @param eventId id of the event (Primary Key)
     * @param eventName String: name of the event
     * @param description String: description of what the event is
     * @param destinationId int: id of the destination where the event is (FK)
     * @param startDate Date: start date of te event
     * @param endDate Date: end date of the event
     * @param ageRestriction int: minimum age restriction of the event
     */
    public Events(int eventId, String eventName, String description, int destinationId, Date startDate, Date endDate,
                  int ageRestriction){
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.destinationId = destinationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ageRestriction = ageRestriction;
    }

    /**
     * Method to turn the age restriction integer into a readable string that can be displayed on the front end
     * @return String of the age restriction is there is one
     */
    public String getAgeRestrictionString(){
        if (ageRestriction == 0){
            return "All ages.";
        } else {
            return Integer.toString(ageRestriction) + "+";
        }
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setEventGenres(List<MusicGenre> genres) { this.eventGenres = genres;}

    public void setEventArtists(List<Artist> artists) {this.eventArtists = artists;}

    public void setEventTypes(List<String> types) {this.eventTypes = types;}

    public List<MusicGenre> getEventGenres(){return this.eventGenres;}

    public List<Artist> getEventArtists(){return this.eventArtists;}

    public List<String> getEventTypes(){return this.eventTypes;}

    public String getArtistForm() {
        return artistForm;
    }

    public String getGenreForm() {
        return genreForm;
    }

    public String getTypeForm() {
        return typeForm;
    }

    public String getAgeForm() {
        return ageForm;
    }

    public void setArtistForm(String artistForm) {
        this.artistForm = artistForm;
    }

    public void setGenreForm(String genreForm) {
        this.genreForm = genreForm;
    }

    public void setTypeForm(String typeForm) {
        this.typeForm = typeForm;
    }

    public void setAgeForm(String ageForm) {
        this.ageForm = ageForm;
    }
}
