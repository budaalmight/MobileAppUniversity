package user.mobileappuni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import user.mobileappuni.adapters.ProductsAdapter;
import user.mobileappuni.models.Product;

import java.util.ArrayList;
import java.util.Collections;
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


        final YaasRestClient client = new YaasRestClient();
        client.getProducts(getApplicationContext(), LoginActivity.getToken(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    JSONArray productsForBoxing = new JSONArray();
                    JSONArray productsForSwimming = new JSONArray();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject product = response.getJSONObject(i).getJSONObject("product");
                        String productPrice = response.getJSONObject(i).getJSONArray("prices").getJSONObject(0)
                                .getString("effectiveAmount");
                        product.put("price", productPrice);
                        if (HomeActivity.getSport() == "Boxing" && product.getString("sku").startsWith("1")) {
                            productsForBoxing.put(product);
                        } else {
                            productsForSwimming.put(product);
                        }
                    }
                    final ArrayList<Product> products;
                    if (HomeActivity.getSport() == "Boxing") {
                        products = getRandomProducts(productsForBoxing);
                    } else {
                        products = getRandomProducts(productsForSwimming);
                    }

                    GridView gridview = (GridView) findViewById(R.id.recommendationsGrid);
                    gridview.setAdapter(new ProductsAdapter(RecommendationsActivity.this, products) {});

                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                            Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);

                            intent.putExtra("productId", products.get(position).getId());
                            intent.putExtra("productTitle", products.get(position).getName());
                            intent.putExtra("productDescription", products.get(position).getDescription());
                            intent.putExtra("productPrice", products.get(position).getPrice());
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private ArrayList<Product> getRandomProducts(JSONArray products) throws JSONException {
        ArrayList<Product> randomProducts = new ArrayList<>();
        ArrayList<Integer> randomValues = new ArrayList<>();
        for(int i = 0; i < products.length(); i++) {
            randomValues.add(i);
        }
        Collections.shuffle(randomValues);
        if (products.length() > 2) {
            for (int i = 0; i < 3; i++) {
                JSONObject productJson = products.getJSONObject(randomValues.get(i));
                Product product = new Product(productJson.getString("id"),
                        productJson.getString("name"),
                        ContextCompat.getDrawable(this, getResources().getIdentifier("m" + productJson.getString("id"), "drawable", getPackageName())),
                        productJson.getString("description"),
                        "$ " + productJson.getString("price"));
                randomProducts.add(product);
            }
        } else {
            for (int i = 0; i < products.length(); i++) {
                JSONObject productJson = products.getJSONObject(randomValues.get(i));
                Product product = new Product(productJson.getString("id"),
                        productJson.getString("name"),
                        ContextCompat.getDrawable(this, getResources().getIdentifier("m" + productJson.getString("id"), "drawable", getPackageName())),
                        productJson.getString("description"),
                        "$ " + productJson.getString("prices"));
                randomProducts.add(product);
            }
        }

        return randomProducts;
    }
}
