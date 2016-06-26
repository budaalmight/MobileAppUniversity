package user.mobileappuni.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import user.mobileappuni.HomeActivity;
import user.mobileappuni.LoginActivity;
import user.mobileappuni.OurDialog;
import user.mobileappuni.R;
import user.mobileappuni.YaasRestClient;
import user.mobileappuni.models.Place;

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
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.places_list_item, parent, false);
        final Place place = places.get(position);

        ImageView placeImage = (ImageView) rowView.findViewById(R.id.placeImage);
        placeImage.setImageDrawable(place.getPic());

        TextView placeTitle = (TextView) rowView.findViewById(R.id.placeTitle);
        placeTitle.setText(place.getName());

        TextView placeDescription = (TextView) rowView.findViewById(R.id.placeDescription);
        placeDescription.setText(place.getDescription());

        ImageButton plus = (ImageButton) rowView.findViewById(R.id.addVisit);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final YaasRestClient client = new YaasRestClient();
                client.addVisit(rowView.getContext(), LoginActivity.getToken(), LoginActivity.getUsername(), place.getSport(), place.getName(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        client.getVisits(getContext(), LoginActivity.getToken(), place.getSport(), new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                                final String otherSport;
                                if(place.getSport().equals("Boxing")){
                                    otherSport = "Swimming";
                                }
                                else{
                                    otherSport = "Boxing";
                                }
                                client.getVisits(getContext(),LoginActivity.getToken(),otherSport,new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody){
                                        if(responseBody.length()>= response.length()){
                                            HomeActivity.setSport(otherSport);
                                        }
                                        else {
                                            HomeActivity.setSport(place.getSport());
                                        }
                                        Intent intent = new Intent(rowView.getContext(), OurDialog.class);
                                        intent.putExtra("visits",response.length());
                                        rowView.getContext().startActivity(intent);
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println("something went wrong");
                    }
                });
            }
        });

        return rowView;
    }
}
