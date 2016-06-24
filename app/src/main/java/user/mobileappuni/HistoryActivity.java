package user.mobileappuni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kanch on 6/24/2016.
 */
public class HistoryActivity extends Activity {
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
    }
}
