package com.example.sarvesh.i_turnout;

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

import java.util.List;

public class ViewEnrollCustomAdapter extends BaseAdapter {

    Context context;
    List<ViewEnrollRowItem> rowItems;

    ViewEnrollCustomAdapter(Context context, List<ViewEnrollRowItem> rowItems) {
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

        TextView studentid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.viewlist, null);
            holder = new ViewHolder();

            holder.studentid = (TextView) convertView
                    .findViewById(R.id.studentid);


            ViewEnrollRowItem row_pos = rowItems.get(position);


            holder.studentid.setText(row_pos.getStudentId());


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}