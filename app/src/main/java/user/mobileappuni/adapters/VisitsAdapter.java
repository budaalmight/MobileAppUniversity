package user.mobileappuni.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import user.mobileappuni.R;

import java.util.List;

/**
 * Created by kanch on 6/25/2016.
 */
public class VisitsAdapter extends ArrayAdapter<Object> {
    private final Context mContext;
    private final List<Object> visits;

    public VisitsAdapter(Context mContext, List<Object> visits) {
        super(mContext, R.layout.history_list_item, visits);
        this.mContext = mContext;
        this.visits = visits;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.history_list_item, parent, false);

        //TODO: Add logic where the components of history_list_item layout are set from the given visits

        return rowView;
    }
}
