package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DeleteTeacherAdapter extends BaseAdapter {
    private Context context;
    private List<DeleteTeacherItem> rowItems;
    private List<String> deleteTeacher;
    private ArrayList<Boolean>positionArray;
    DeleteTeacherAdapter(Context context, List<DeleteTeacherItem> rowItems, List<String> deleteTeacher) {
        this.context = context;
        this.rowItems = rowItems;
        this.deleteTeacher=deleteTeacher;
        positionArray=new ArrayList<Boolean>(rowItems.size());
        for(int j=0;j<rowItems.size();j++)
        {
            positionArray.add(false);
        }
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

        TextView teacherName;
        TextView teacherId;
        TextView tDate;
        CheckBox checkBox;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final DeleteTeacherAdapter.ViewHolder holder;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.deleteteacherlist, null);
            holder = new DeleteTeacherAdapter.ViewHolder();
            holder.teacherName= convertView
                   .findViewById(R.id.delTeacher4);
            holder.teacherId=convertView.findViewById(R.id.tNotificationId1);
            holder.tDate=convertView.findViewById(R.id.tDate1);
            holder.checkBox=convertView.findViewById(R.id.tCheck6);
          //  rowP = rowItems.get(position);
           // DeleteTeacherItem row_pos = rowItems.get(position);
            holder.teacherName.setText(rowItems.get(position).getTeacherName());
            holder.teacherId.setText(rowItems.get(position).getTeacherId());
            holder.tDate.setText(rowItems.get(position).getDate());

            convertView.setTag(holder);
        } else {
            holder = (DeleteTeacherAdapter.ViewHolder) convertView.getTag();
            holder.checkBox.setOnCheckedChangeListener(null);
        }
        holder.checkBox.setFocusable(false);
        holder.teacherName= convertView
                .findViewById(R.id.delTeacher4);
        holder.teacherId=convertView.findViewById(R.id.tNotificationId1);
        holder.tDate=convertView.findViewById(R.id.tDate1);
        holder.checkBox=convertView.findViewById(R.id.tCheck6);
        holder.checkBox.setChecked(positionArray.get(position));
        //  rowP = rowItems.get(position);
        // DeleteTeacherItem row_pos = rowItems.get(position);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    positionArray.set(position,true);
                }
                else
                    positionArray.set(position,false);
            }
        });
        holder.teacherName.setText(rowItems.get(position).getTeacherName());
        holder.teacherId.setText(rowItems.get(position).getTeacherId());
        holder.tDate.setText(rowItems.get(position).getDate());
        final ViewHolder finalHolder = holder;
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalHolder.checkBox.isChecked())
                    deleteTeacher.add(finalHolder.teacherId.getText().toString());
                else
                    deleteTeacher.add(finalHolder.teacherId.getText().toString());

            }
        });
        return convertView;
    }
    public void setfilter(List<DeleteTeacherItem> newList)
    {
        //  Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
        rowItems=new ArrayList<>();
        rowItems.addAll(newList);
        notifyDataSetChanged();
        //Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
    }
}
