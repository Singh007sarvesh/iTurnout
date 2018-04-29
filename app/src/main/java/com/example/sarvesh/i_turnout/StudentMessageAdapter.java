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

public class StudentMessageAdapter extends BaseAdapter{
    Context context;
    List<StudentMessageItem> rowItems;

    StudentMessageAdapter(Context context, List<StudentMessageItem> rowItems) {
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

        ImageView setIcon;
        TextView name;
        TextView tid;
        TextView sDate;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StudentMessageAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.messagelistforstudent, null);
            holder = new StudentMessageAdapter.ViewHolder();

            holder.name =  convertView
                    .findViewById(R.id.studentMessageText);
            holder.setIcon= convertView.findViewById(R.id.sSetIcon);
           holder.tid=convertView.findViewById(R.id.messageListDate);
           holder.sDate=convertView.findViewById(R.id.studentGetMessageDate);

            StudentMessageItem row_pos = rowItems.get(position);

            holder.setIcon.setImageResource(row_pos.getPicture());
            holder.name.setText(row_pos.getUserName());
            holder.tid.setText(row_pos.getUserId());
            holder.sDate.setText(row_pos.getDate());

            convertView.setTag(holder);
        } else {
            holder = (StudentMessageAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
