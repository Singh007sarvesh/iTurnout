package com.example.sarvesh.i_turnout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AttendanceCustomAdapter extends BaseAdapter implements Filterable{

    Context context;
    List<AttendanceItemRow> rowItems;
    private LayoutInflater inflater;
    List<AttendanceItemRow> mStringFilterList;
    ValueFilter valueFilter;
    AttendanceCustomAdapter(Context context, List<AttendanceItemRow> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
        mStringFilterList = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /* private view holder class */
  /* private class ViewHolder {


        TextView rollNumber;


    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.makeitemlist, null);
        }

        TextView txtName = convertView.findViewById(R.id.rollno);

        AttendanceItemRow bean = rowItems.get(position);
        String name = bean.getRollNumber();
        txtName.setText(name);

        return convertView;
    }
    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }
    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<AttendanceItemRow> filterList = new ArrayList<AttendanceItemRow>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getRollNumber().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        AttendanceItemRow bean = new AttendanceItemRow(mStringFilterList.get(i)
                                .getRollNumber()
                        );
                        filterList.add(bean);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            rowItems = (ArrayList<AttendanceItemRow>) results.values;
            notifyDataSetChanged();
        }

    }
}
