package user.mobileappuni.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import user.mobileappuni.R;
import user.mobileappuni.beans.Place;

import java.util.List;

/**
 * Created by kanch on 6/25/2016.
 */
public class PlacesAdapter extends ArrayAdapter<Object> {
    private final Context mContext;
    private final List<Place> places;

    public PlacesAdapter(Context mContext, List<Place> places) {
        super(mContext, R.layout.places_list_item);
        this.mContext = mContext;
        this.places = places;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.places_list_item, parent, false);

        //TODO: Add logic where the components of places_list_item layout are set from the given places

        //TODO: Add logic for what will happen when a +Button is clicked

        return rowView;
    }
}
