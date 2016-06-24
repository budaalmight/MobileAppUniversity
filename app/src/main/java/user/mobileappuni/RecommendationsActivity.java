package user.mobileappuni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by kanch on 6/24/2016.
 */
public class RecommendationsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendations_list);

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
