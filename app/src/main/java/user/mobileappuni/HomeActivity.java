package user.mobileappuni;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by kanch on 6/24/2016.
 */
public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_list);
        ListView places = (ListView) findViewById(R.layout.places_list);
    }
}
