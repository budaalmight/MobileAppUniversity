package user.mobileappuni.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import user.mobileappuni.R;
import user.mobileappuni.models.Place;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanch on 6/25/2016.
 */
public class PlacesAdapter extends ArrayAdapter<Place> {
    private final Context mContext;
    private final ArrayList<Place> places;

    public PlacesAdapter(Context mContext, ArrayList<Place> places) {
        super(mContext, R.layout.places_list_item, places);
        this.mContext = mContext;
        this.places = places;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.places_list_item, parent, false);
        Place place = places.get(position);

        ImageView placeImage = (ImageView) rowView.findViewById(R.id.placeImage);
        placeImage.setImageDrawable(place.getPic());

        TextView placeTitle = (TextView) rowView.findViewById(R.id.placeTitle);
        placeTitle.setText(place.getName());

        TextView placeDescription = (TextView) rowView.findViewById(R.id.placeDescription);
        placeDescription.setText(place.getDescription());

        //TODO: Add logic for what will happen when a +Button is clicked

        return rowView;
    }
}
