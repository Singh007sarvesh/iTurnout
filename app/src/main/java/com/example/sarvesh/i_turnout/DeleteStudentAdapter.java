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

public class DeleteStudentAdapter extends BaseAdapter {

    private Context context;
    private List<DeleteStudentItem> rowItems;
    private List<String> deleteStudent;
    private ArrayList<Boolean>positionArray;


    DeleteStudentAdapter(Context context, List<DeleteStudentItem> rowItems, List<String> deleteStudent) {
        this.context = context;
        this.rowItems = rowItems;
        this.deleteStudent=deleteStudent;
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

    public class ViewHolder {

        TextView studentName;
        TextView studentId;
        TextView sDate;
        CheckBox StudentCheckBox;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
         DeleteStudentAdapter.ViewHolder holder=null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.deletestudentlist, null);
            holder = new DeleteStudentAdapter.ViewHolder();
            holder.studentName= convertView
                    .findViewById(R.id.delStudentData);
            holder.studentId=convertView.findViewById(R.id.studentDeleteData);
            holder.sDate=convertView.findViewById(R.id.deleteStudentDate1);
            holder.StudentCheckBox=convertView.findViewById(R.id.studentCheck2);
            //  rowP = rowItems.get(position);
            // DeleteTeacherItem row_pos = rowItems.get(position);
            holder.studentName.setText(rowItems.get(position).getStudentName());
            holder.studentId.setText(rowItems.get(position).getStudentId());
            holder.sDate.setText(rowItems.get(position).getsDate());

            convertView.setTag(holder);
        } else {
            holder = (DeleteStudentAdapter.ViewHolder) convertView.getTag();
            holder.StudentCheckBox.setOnCheckedChangeListener(null);
        }
        holder.StudentCheckBox.setFocusable(false);
        holder.studentName= convertView
                .findViewById(R.id.delStudentData);
        holder.studentId=convertView.findViewById(R.id.studentDeleteData);
        holder.sDate=convertView.findViewById(R.id.deleteStudentDate1);
        holder.StudentCheckBox=convertView.findViewById(R.id.studentCheck2);
        holder.StudentCheckBox.setChecked(positionArray.get(position));


        holder.studentName.setText(rowItems.get(position).getStudentName());
        holder.studentId.setText(rowItems.get(position).getStudentId());
        holder.sDate.setText(rowItems.get(position).getsDate());
        //  rowP = rowItems.get(position);
        // DeleteTeacherItem row_pos = rowItems.get(position);
        holder.StudentCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
      //  return convertView;
        final ViewHolder finalHolder = holder;
        holder.StudentCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(finalHolder.StudentCheckBox.isChecked())
                    deleteStudent.add(finalHolder.studentId.getText().toString());
                else
                    deleteStudent.remove(finalHolder.studentId.getText().toString());



            }
        });
        return convertView;
    }
    public void setfilter(List<DeleteStudentItem> newList)
    {
        //  Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
        rowItems=new ArrayList<>();
        rowItems.addAll(newList);
        notifyDataSetChanged();
        //Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
    }
}
