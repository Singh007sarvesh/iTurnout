package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DeleteTeacherAdapter extends BaseAdapter {
    Context context;
    List<DeleteTeacherItem> rowItems;

    DeleteTeacherAdapter(Context context, List<DeleteTeacherItem> rowItems) {
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

        TextView studentName;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DeleteTeacherAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.deleteteacherlist, null);
            holder = new DeleteTeacherAdapter.ViewHolder();

            holder.studentName = convertView
                    .findViewById(R.id.delteacher);


            DeleteTeacherItem row_pos = rowItems.get(position);


            holder.studentName.setText(row_pos.getTeacherName());



            convertView.setTag(holder);
        } else {
            holder = (DeleteTeacherAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
