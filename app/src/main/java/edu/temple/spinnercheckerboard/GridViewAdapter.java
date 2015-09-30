package edu.temple.spinnercheckerboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Kevin on 9/17/15.
 */
public class GridViewAdapter extends BaseAdapter {
    private int columns;
    private String[] gridValues;
    private Context context;

    GridViewAdapter(Context context, int columns){
        int colSqr = columns*columns;
        this.columns = columns;
        this.context = context;

        gridValues = new String[colSqr];

        for(int i=0;i<colSqr;i++){
            gridValues[i] = ""+(i+1);
        }
    }

    @Override
    public int getCount() {
        return columns*columns;
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
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.fragment_checkerboard, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.gridText);
            textView.setText(gridValues[position]);

            if((((position/columns) + (position%columns))%2 == 0)){
                textView.setBackgroundColor(context.getResources().getColor(android.R.color.black));
                textView.setTextColor(context.getResources().getColor(android.R.color.white));
            }
            else{
                textView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                textView.setTextColor(context.getResources().getColor(android.R.color.black));
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
