package user.mobileappuni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import user.mobileappuni.adapters.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

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

        //TODO: Get the products from YaaS
        List<Object> products = new ArrayList<>();
        products.add(new Object());
        products.add(new Object());
        products.add(new Object());
        products.add(new Object());
        products.add(new Object());
        products.add(new Object());
        products.add(new Object());

        GridView gridview = (GridView) findViewById(R.id.recommendationsGrid);
        gridview.setAdapter(new ProductsAdapter(this, products) {
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);

                //TODO: pass the needed data via the intent
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }
}
