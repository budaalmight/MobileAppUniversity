package user.mobileappuni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kanch on 6/24/2016.
 */
public class ProductDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        final ImageButton backButton = (ImageButton) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RecommendationsActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        ImageView productImage = (ImageView) findViewById(R.id.mainImage);
        productImage.setImageDrawable(ContextCompat.getDrawable(this, getResources().getIdentifier("m" + intent.getStringExtra("productId"), "drawable", getPackageName())));

        TextView productTitle = (TextView) findViewById(R.id.productTitle);
        productTitle.setText(intent.getStringExtra("productTitle"));

        TextView productDescription = (TextView) findViewById(R.id.productDescription);
        productDescription.setText(intent.getStringExtra("productDescription"));

        TextView productPrice = (TextView) findViewById(R.id.productPrice);
        productPrice.setText(intent.getStringExtra("productPrice"));
    }
}
