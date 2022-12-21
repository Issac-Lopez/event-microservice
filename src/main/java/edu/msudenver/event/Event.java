package edu.msudenver.event;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.venue.Venue;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "events")
public class Event implements Serializable {
    @Column(name = "title")
    private String title;
    @Id
    @Column(name = "event_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    @Column(name = "starts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date starts;
    @Column(name = "ends")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ends;

    @Column(name = "venue_id") // foreign key not composite
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long venueId;
    public Event(String title, Date starts, Date ends, Long venueId) {
        this.title = title;
        this.starts = starts;
        this.ends = ends;
        this.venueId = venueId;
    }
    public Event() {}
    public String getTitle() {
        return this.title;
    }
    public Date getStarts() {
        return this.starts;
    }
    public Date setStarts(Date starts) {
        return this.starts;
    }
    public Date getEnds() {
        return this.ends;
    }
    public Date setEnds(Date ends) {
        return this.ends;
    }
    public Long getEventId() {
        return this.eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    public Event setTitle(String title) {
        this.title = title;
        return this;
    }
    public Long getVenueId() {
        return this.venueId;
    }
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
}