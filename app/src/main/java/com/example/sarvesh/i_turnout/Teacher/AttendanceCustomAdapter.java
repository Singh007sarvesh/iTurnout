package com.example.sarvesh.i_turnout.Teacher;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sarvesh.i_turnout.AttendanceItemRow;
import com.example.sarvesh.i_turnout.R;

import java.util.ArrayList;
import java.util.List;

public class AttendanceCustomAdapter extends BaseAdapter {

    private Context context;
    private List<AttendanceItemRow> rowItems;
    private List<String> absent;
    private ArrayList<Boolean>positionArray;
   // private ValueFilter valueFilter;
    AttendanceCustomAdapter(Context context, List<AttendanceItemRow> rowItems , List<String> absent) {
        this.context = context;
        this.rowItems = rowItems;
        this.absent = absent;
        positionArray=new ArrayList<Boolean>(rowItems.size());
        for(int j=0;j<rowItems.size();j++)
        {
            positionArray.add(true);
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
    public long getItemId(int position)
    {
        return position;
    }



    /* private view holder class */
   private class ViewHolder {

        TextView rollNumber;
        TextView attendanceId;
        CheckBox checkBox;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        AttendanceCustomAdapter.ViewHolder holder = null;
        AttendanceItemRow row_pos = null;
      //  absent = new ArrayList<>();

     //   checkBox = (CheckBox) convertView.findViewById(R.id.check1);
      //  holder.rollNumber = (TextView) convertView.findViewById(R.id.rollNo);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.makeitemlist, null);
            holder = new AttendanceCustomAdapter.ViewHolder();

            holder.rollNumber = convertView
                    .findViewById(R.id.rollNo);
            holder.attendanceId=convertView.findViewById(R.id.attendanceId);
            holder.checkBox = convertView
                    .findViewById(R.id.check1);
             row_pos = rowItems.get(position);
            holder.rollNumber.setText(row_pos.getStudentName());
            holder.attendanceId.setText(row_pos.getRollNumber());
            convertView.setTag(holder);
        } else {
            holder = (AttendanceCustomAdapter.ViewHolder) convertView.getTag();
            holder.checkBox.setOnCheckedChangeListener(null);
        }
        holder.checkBox.setFocusable(false);

        row_pos = rowItems.get(position);
        holder.rollNumber.setText(row_pos.getStudentName());
        holder.attendanceId.setText(row_pos.getRollNumber());
        holder.checkBox.setChecked(positionArray.get(position));
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

        //Toast.makeText(context,holder.rollNumber.getText().toString(),Toast.LENGTH_SHORT).show();
        final ViewHolder finalHolder = holder;
        //final AttendanceItemRow finalRow_pos = row_pos;
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalHolder.checkBox.isChecked())
                {
                    absent.remove(finalHolder.attendanceId.getText().toString());
                  //  Toast.makeText(context,"checked"+finalHolder.attendanceId.getText().toString(),Toast.LENGTH_SHORT).show();

                }
                else {
                    absent.add(finalHolder.attendanceId.getText().toString());

                   // Toast.makeText(context,"unchecked"+finalHolder.attendanceId.getText().toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });

        return convertView;
    }




    public void setFilter(List<AttendanceItemRow> newList)
    {
        rowItems=new ArrayList<>();
        rowItems.addAll(newList);
        notifyDataSetChanged();
    }
}
