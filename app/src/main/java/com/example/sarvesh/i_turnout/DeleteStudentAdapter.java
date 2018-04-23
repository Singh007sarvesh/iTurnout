package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteStudentAdapter extends BaseAdapter {
    Context context;
    List<DeleteItemforStudent> rowItems;

    DeleteStudentAdapter(Context context, List<DeleteItemforStudent> rowItems) {
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

        DeleteStudentAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.deletestudentlist, null);
            holder = new DeleteStudentAdapter.ViewHolder();

            holder.studentName = convertView
                    .findViewById(R.id.delstudent);


            DeleteItemforStudent row_pos = rowItems.get(position);


            holder.studentName.setText(row_pos.getStudentName());



            convertView.setTag(holder);
        } else {
            holder = (DeleteStudentAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    public void setFilter(List<DeleteItemforStudent> newList)
    {
      //  Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
        rowItems=new ArrayList<>();
        rowItems.addAll(newList);
        notifyDataSetChanged();
        //Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
    }
}
