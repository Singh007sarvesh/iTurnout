package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ViewNotificationCustomAdapter extends BaseAdapter {

    Context context;
    List<ViewNotificationItem> rowItems;

    ViewNotificationCustomAdapter(Context context, List<ViewNotificationItem> rowItems) {
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

        ImageView seticon;
        TextView name;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewNotificationCustomAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.viewnotificationlist, null);
            holder = new ViewHolder();

            holder.name = (TextView) convertView
                    .findViewById(R.id.notification);
            holder.seticon=(ImageView) convertView.findViewById(R.id.seticon);

            ViewNotificationItem row_pos = rowItems.get(position);

            holder.seticon.setImageResource(row_pos.getPicture());
            holder.name.setText(row_pos.getName());


            convertView.setTag(holder);
        } else {
            holder = (ViewNotificationCustomAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}