package com.example.sarvesh.i_turnout;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


public class CheckAttStudentCustomAdapter extends BaseAdapter{
    Context context;
    List<CheckAttByStudentRowItem> rowItems;

    CheckAttStudentCustomAdapter( Context context,List<CheckAttByStudentRowItem> rowItems) {
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
    public static class ViewHolder {

        TextView subjectname;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CheckAttStudentCustomAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.checkattbystudentlistitem, null);
            holder = new CheckAttStudentCustomAdapter.ViewHolder();

            holder.subjectname = (TextView) convertView
                    .findViewById(R.id.checkattbystudent);


            CheckAttByStudentRowItem row_pos = rowItems.get(position);


            holder.subjectname.setText(row_pos.getSubjectname());


            convertView.setTag(holder);
        } else {
            holder = (CheckAttStudentCustomAdapter.ViewHolder) convertView.getTag();
        }

        holder.subjectname = (TextView) convertView
                .findViewById(R.id.checkattbystudent);
        CheckAttByStudentRowItem row_pos = rowItems.get(position);
        holder.subjectname.setText(row_pos.getSubjectname());
        return convertView;
    }

}