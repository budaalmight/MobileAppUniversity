package user.mobileappuni.models;

import android.graphics.drawable.Drawable;

public class Place {
    private String name;
    private Drawable pic;
    private String description;
    private String sport;

    public Place(String name, Drawable pic, String description, String sport) {
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public Drawable getPic() {
        return pic;
    }

    public String getDescription() {
        return description;
    }

    public String getSport() {
        return sport;
    }
}
