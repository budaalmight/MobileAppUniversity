package user.mobileappuni;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import user.mobileappuni.adapters.VisitsAdapter;
import user.mobileappuni.models.Visit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by kanch on 6/24/2016.
 */
public class HistoryActivity extends ListActivity {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

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

        final YaasRestClient client = new YaasRestClient();
        client.getVisits(getApplicationContext(), LoginActivity.getToken(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<Visit> visits = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject visitJson = response.getJSONObject(i);
                        Visit visit = new Visit(visitJson.getString("user"), visitJson.getString("sport"),
                                visitJson.getString("place"), format.parse(visitJson.getString("date")));
                        visits.add(visit);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                setListAdapter(new VisitsAdapter(HistoryActivity.this, visits));
            }
        });
    }
}
