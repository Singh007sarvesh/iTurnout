package com.example.sarvesh.i_turnout.Teacher;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sarvesh.i_turnout.R;

import java.util.List;

public class TeacherCheckAdapter extends BaseAdapter {


    private Context context;
    private List<TeacherCheckItem> rowItems;

    TeacherCheckAdapter(Context context, List<TeacherCheckItem> rowItems) {
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

    public class ViewHolder {

        TextView studentName;
        TextView studentId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeacherCheckAdapter.ViewHolder viewHolder=null;

        LayoutInflater layoutInflater=(LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null) {
            convertView = layoutInflater.inflate(R.layout.activity_teachercheckitem, null);
            viewHolder = new TeacherCheckAdapter.ViewHolder();

            viewHolder.studentName = convertView.findViewById(R.id.teacherCheck3);
            viewHolder.studentId = convertView.findViewById(R.id.teacherCheck4);
            int x= Integer.parseInt(rowItems.get(position).getTotalAttendance());
              if(x<81) {
                  convertView.setBackgroundColor(Color.parseColor("#F7AEAB"));
                  viewHolder.studentName.setText(rowItems.get(position).getStudentName());
                  viewHolder.studentId.setText(rowItems.get(position).getStudentId());
              }
              else
              {
                  viewHolder.studentName.setText(rowItems.get(position).getStudentName());
                  viewHolder.studentId.setText(rowItems.get(position).getStudentId());
              }
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(TeacherCheckAdapter.ViewHolder)convertView.getTag();
        }

        viewHolder.studentName = convertView.findViewById(R.id.teacherCheck3);
        viewHolder.studentId = convertView.findViewById(R.id.teacherCheck4);

        viewHolder.studentName.setText(rowItems.get(position).getStudentName());
        viewHolder.studentId.setText(rowItems.get(position).getStudentId());
        return convertView;
    }
}
