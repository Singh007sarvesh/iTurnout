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

public class TViewNotificationCusAdapter extends BaseAdapter {

    Context context;
    List<TViewNotificationItem> rowItems;

    TViewNotificationCusAdapter(Context context, List<TViewNotificationItem> rowItems) {
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

        ImageView tseticon;
        TextView tname;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TViewNotificationCusAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tviewnotificationlist, null);
            holder = new ViewHolder();

            holder.tname = (TextView) convertView
                    .findViewById(R.id.tnotification);
            holder.tseticon=(ImageView) convertView.findViewById(R.id.tseticon);

            TViewNotificationItem row_pos = rowItems.get(position);

            holder.tseticon.setImageResource(row_pos.getPicture());
            holder.tname.setText(row_pos.getName());


            convertView.setTag(holder);
        } else {
            holder = (TViewNotificationCusAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}