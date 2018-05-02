package com.example.sarvesh.i_turnout;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
public class CheckEnStudInPerSubCustomAdapter extends BaseAdapter{
    Context context;
    List<CheckEnStudInPerSubRowItem> rowItems;

    CheckEnStudInPerSubCustomAdapter(Context context, List<CheckEnStudInPerSubRowItem> rowItems) {
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
    class ViewHolder {

        TextView studentname;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CheckEnStudInPerSubCustomAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.checkenstudinpersublistitem, null);
            holder = new CheckEnStudInPerSubCustomAdapter.ViewHolder();

            holder.studentname = convertView
                    .findViewById(R.id.enrollmentinperticulersubject);


            CheckEnStudInPerSubRowItem row_pos = rowItems.get(position);


            holder.studentname.setText(row_pos.getStudentname());


            convertView.setTag(holder);
        } else {
            holder= (CheckEnStudInPerSubCustomAdapter.ViewHolder) convertView.getTag();
        }

        holder.studentname =  convertView
                .findViewById(R.id.enrollmentinperticulersubject);
        CheckEnStudInPerSubRowItem row_pos = rowItems.get(position);
        holder.studentname.setText(row_pos.getStudentname());
        return convertView;
    }
}