package user.mobileappuni;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;
import user.mobileappuni.adapters.PlacesAdapter;
import user.mobileappuni.models.Place;

import java.util.ArrayList;

/**
 * Created by kanch on 6/24/2016.
 */
public class HomeActivity extends ListActivity {
    private static String sport;
    static{

    }

    public static String getSport() {
        return sport;
    }

    public static void setSport(String sport) {
        HomeActivity.sport = sport;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_list);
        final YaasRestClient client = new YaasRestClient();
        final String firstSport = "Boxing";
        final TextView recommendations = (TextView) findViewById(R.id.recommendations);
        client.getVisits(this.getBaseContext(), LoginActivity.getToken(),firstSport , new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                final String otherSport;
                if (firstSport.equals("Boxing")) {
                    otherSport = "Swimming";
                } else {
                    otherSport = "Boxing";
                }
                client.getVisits(recommendations.getContext(), LoginActivity.getToken(), otherSport, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                        if (responseBody.length() >= response.length()) {
                            HomeActivity.setSport(otherSport);
                        } else {
                            HomeActivity.setSport(firstSport);
                        }
                    }
                });
            }
        });
        final TextView history = (TextView) findViewById(R.id.history);

        recommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RecommendationsActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Place> places = new ArrayList<>();
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM PLACES", null);
        Place place;
        while(c.moveToNext()){
            int drawableId = getResources().getIdentifier(c.getString(1), "drawable", getPackageName());
            place = new Place(c.getString(0), ContextCompat.getDrawable(this, drawableId), c.getString(2), c.getString(3));
            places.add(place);
        }
        c.close();
        database.close();
        helper.close();

        setListAdapter(new PlacesAdapter(this, places));
    }
}
