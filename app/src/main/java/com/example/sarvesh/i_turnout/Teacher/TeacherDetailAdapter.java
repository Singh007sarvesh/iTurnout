package com.example.sarvesh.i_turnout.Teacher;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sarvesh.i_turnout.R;

import java.util.List;

public class TeacherDetailAdapter extends BaseAdapter {

    Context context;
    List<TeacherDetailRowItem> rowItems;

    public TeacherDetailAdapter(Context context, List<TeacherDetailRowItem> rowItems) {
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


        TextView teacherName;
        TextView teacherId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TeacherDetailAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.teacherdetaillist, null);
            holder = new TeacherDetailAdapter.ViewHolder();

            holder.teacherName = convertView
                    .findViewById(R.id.teacherdetail);
            holder.teacherId=convertView.findViewById(R.id.teacherDetail6);


            TeacherDetailRowItem row_pos = rowItems.get(position);

            holder.teacherName.setText(row_pos.getTeacherName());
            holder.teacherId.setText(row_pos.getTeacherId());


            convertView.setTag(holder);
        } else {
            holder = (TeacherDetailAdapter.ViewHolder) convertView.getTag();
        }

        holder.teacherName = convertView
                .findViewById(R.id.teacherdetail);
        holder.teacherId=convertView.findViewById(R.id.teacherDetail6);
        TeacherDetailRowItem row_pos = rowItems.get(position);

        holder.teacherName.setText(row_pos.getTeacherName());
        holder.teacherId.setText(row_pos.getTeacherId());
        return convertView;
    }
}
