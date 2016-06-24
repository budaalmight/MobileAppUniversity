package user.mobileappuni;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import user.mobileappuni.adapters.VisitsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanch on 6/24/2016.
 */
public class HistoryActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_list);
        final TextView home = (TextView) findViewById(R.id.home);
        final TextView recommendations = (TextView) findViewById(R.id.recommendations);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        recommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RecommendationsActivity.class);
                startActivity(intent);
            }
        });

        //TODO: Get the visits from YaaS custom document in Persistence Service
        List<Object> visits = new ArrayList<>();
        visits.add(new Object());
        visits.add(new Object());
        visits.add(new Object());
        visits.add(new Object());

        setListAdapter(new VisitsAdapter(this, visits));
    }
}
