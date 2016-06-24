package user.mobileappuni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kanch on 6/24/2016.
 */
public class RecommendationsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendations_list);
        final TextView home = (TextView) findViewById(R.id.home);
        final TextView history = (TextView) findViewById(R.id.history);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
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

        GridView gridview = (GridView) findViewById(R.id.recommendationsGrid);
//        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(RecommendationsActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
