package user.mobileappuni.models;

import java.util.Date;

/**
 * Created by kanch on 6/25/2016.
 */
public class Visit {
    private String user;
    private String sport;
    private String place;
    private Date date;

    public Visit(String user, String sport, String place, Date date) {
        this.user = user;
        this.sport = sport;
        this.place = place;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
