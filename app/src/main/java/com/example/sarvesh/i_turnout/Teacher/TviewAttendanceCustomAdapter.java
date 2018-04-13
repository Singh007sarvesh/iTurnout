package com.example.sarvesh.i_turnout.Teacher;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sarvesh.i_turnout.CustomAdapter;
import com.example.sarvesh.i_turnout.R;
import com.example.sarvesh.i_turnout.RowItem;

import java.util.List;

public class TviewAttendanceCustomAdapter extends BaseAdapter{


    Context context;
    List<TViewAttendanceRowItem> rowItems;

    TviewAttendanceCustomAdapter(Context context, List<TViewAttendanceRowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
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
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {

        TextView subjectname;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TviewAttendanceCustomAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tviewattendanceitemlist, null);
            holder = new TviewAttendanceCustomAdapter.ViewHolder();

            holder.subjectname = convertView
                    .findViewById(R.id.tviewattitemlist);


            TViewAttendanceRowItem row_pos = rowItems.get(position);


            holder.subjectname.setText(row_pos.getSubjectname());



            convertView.setTag(holder);
        } else {
            holder = (TviewAttendanceCustomAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
