package user.mobileappuni.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import user.mobileappuni.R;
import user.mobileappuni.models.Place;
import user.mobileappuni.models.Product;

import java.util.ArrayList;

/**
 * Created by kanch on 6/25/2016.
 */
public class ProductsAdapter extends BaseAdapter {
    private Context mContext;
    private final ArrayList<Product> products;

    public ProductsAdapter(Context c, ArrayList<Product> products) {
        this.mContext = c;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            gridView = inflater.inflate(R.layout.recommendations_list_item, null);

            Product product = products.get(position);

            ImageView productImage = (ImageView) gridView.findViewById(R.id.productImage);
            productImage.setImageDrawable(product.getImage());

            TextView productTitle = (TextView) gridView.findViewById(R.id.productTitle);
            productTitle.setText(product.getName());

            TextView productPrice = (TextView) gridView.findViewById(R.id.productPrice);
            productPrice.setText(product.getPrice());

            //TODO: Add logic for what will happen when a product is clicked
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
