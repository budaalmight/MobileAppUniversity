package user.mobileappuni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by kanch on 6/24/2016.
 */
public class HomeActivity extends Activity {
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
        ListView places = (ListView) findViewById(R.id.visitsList);

    }
}
