package user.mobileappuni;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import user.mobileappuni.adapters.PlacesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanch on 6/24/2016.
 */
public class HomeActivity extends ListActivity {
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

        //TODO: Get the places from SQLLiteDB
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM PLACES", null);
        c.moveToFirst();
        for (int j = 1; j < 6; j++) {
            Drawable drawable = getResources().getDrawable(getResources()
                    .getIdentifier("boxing_gym", "drawable", getPackageName()),null);
        }
        c.close();
        database.close();
        helper.close();
        List<Object> places = new ArrayList<>();
        places.add(new Object());
        places.add(new Object());
        places.add(new Object());
        places.add(new Object());
        places.add(new Object());
        places.add(new Object());

        setListAdapter(new PlacesAdapter(this, places));
    }
}
