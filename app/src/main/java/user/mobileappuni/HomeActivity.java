package user.mobileappuni;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import user.mobileappuni.adapters.PlacesAdapter;
import user.mobileappuni.models.Place;

import java.util.ArrayList;

/**
 * Created by kanch on 6/24/2016.
 */
public class HomeActivity extends ListActivity {
    private static String sport;

    public static String getSport() {
        return sport;
    }

    public static void setSport(String sport) {
        HomeActivity.sport = sport;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_list);
        final TextView recommendations = (TextView) findViewById(R.id.recommendations);
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
