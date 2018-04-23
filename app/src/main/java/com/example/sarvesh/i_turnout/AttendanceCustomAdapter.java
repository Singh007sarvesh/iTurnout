package com.example.sarvesh.i_turnout;

import android.app.Activity;
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

public class AttendanceCustomAdapter extends BaseAdapter {

    private Context context;
    private List<AttendanceItemRow> rowItems;
    private List<AttendanceItemRow> mStringFilterList;
   // private ValueFilter valueFilter;
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
   private class ViewHolder {


        TextView rollNumber;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AttendanceCustomAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.makeitemlist, null);
            holder = new AttendanceCustomAdapter.ViewHolder();

            holder.rollNumber = convertView
                    .findViewById(R.id.rollNo);


            AttendanceItemRow row_pos = rowItems.get(position);
            holder.rollNumber.setText(row_pos.getRollNumber());
            convertView.setTag(holder);
        } else {
            holder = (AttendanceCustomAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    public void setFilter(List<AttendanceItemRow> newList)
    {
        rowItems=new ArrayList<>();
        rowItems.addAll(newList);
        notifyDataSetChanged();
    }
}
