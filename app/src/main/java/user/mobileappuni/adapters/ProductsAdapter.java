package user.mobileappuni.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import user.mobileappuni.R;

import java.util.List;

/**
 * Created by kanch on 6/25/2016.
 */
public class ProductsAdapter extends BaseAdapter {
    private Context mContext;
    private final List<Object> products;

    public ProductsAdapter(Context c, List<Object> products) {
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
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.recommendations_list_item, null);

            //TODO: Add logic where the components of recommendations_list_item layout are set from the given products

            //TODO: Add logic for what will happen when a product is clicked
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
