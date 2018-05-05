package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class QueryStudentAdapter extends BaseAdapter {

    Context context;
    List<QueryStudentItem> rowItems;

    QueryStudentAdapter(Context context, List<QueryStudentItem> rowItems) {
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

    public class ViewHolder
    {
        TextView studentName;
        TextView studentId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QueryStudentAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.querystudentdetailsitem, null);
            holder = new QueryStudentAdapter.ViewHolder();

            holder.studentName = convertView
                    .findViewById(R.id.queryDetailsItem6);
            holder.studentId=convertView.findViewById(R.id.queryDetailsItem7);

            holder.studentName.setText(rowItems.get(position).getStudentName());
            holder.studentId.setText(rowItems.get(position).getStudentId());


            convertView.setTag(holder);
        } else {
            holder = (QueryStudentAdapter.ViewHolder) convertView.getTag();
        }

        holder.studentName = convertView
                .findViewById(R.id.queryDetailsItem6);
        holder.studentId=convertView.findViewById(R.id.queryDetailsItem7);

        holder.studentName.setText(rowItems.get(position).getStudentName());
        holder.studentId.setText(rowItems.get(position).getStudentId());

        return convertView;
    }

}
