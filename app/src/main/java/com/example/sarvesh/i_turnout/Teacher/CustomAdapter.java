package com.example.sarvesh.i_turnout.Teacher;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sarvesh.i_turnout.R;
import com.example.sarvesh.i_turnout.RowItem;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItems;

    CustomAdapter(Context context, List<RowItem> rowItems) {
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
    public class ViewHolder {

        TextView subjectname;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.subjectname = convertView
                    .findViewById(R.id.subjectname);


            RowItem row_pos = rowItems.get(position);


            holder.subjectname.setText(row_pos.getSubjectname());



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.subjectname = convertView
                .findViewById(R.id.subjectname);
        RowItem row_pos = rowItems.get(position);
        holder.subjectname.setText(row_pos.getSubjectname());
        return convertView;
    }

}