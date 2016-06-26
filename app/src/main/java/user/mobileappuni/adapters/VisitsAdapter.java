package user.mobileappuni.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import user.mobileappuni.R;
import user.mobileappuni.models.Visit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by kanch on 6/25/2016.
 */
public class VisitsAdapter extends ArrayAdapter<Visit> {
    private final Context mContext;
    private final ArrayList<Visit> visits;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public VisitsAdapter(Context mContext, ArrayList<Visit> visits) {
        super(mContext, R.layout.history_list_item, visits);
        this.mContext = mContext;
        this.visits = visits;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.history_list_item, parent, false);
        Visit visit = visits.get(position);

        TextView visitDate = (TextView) rowView.findViewById(R.id.date);
        visitDate.setText(format.format(visit.getDate()));

        TextView visitPlace = (TextView) rowView.findViewById(R.id.place);
        visitPlace.setText(visit.getPlace());

        TextView visitSport = (TextView) rowView.findViewById(R.id.sport);
        visitSport.setText(visit.getSport());

        return rowView;
    }
}
