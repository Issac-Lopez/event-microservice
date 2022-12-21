package edu.msudenver.event;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.venue.Venue;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
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
}