package com.example.sarvesh.i_turnout.Teacher;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sarvesh.i_turnout.CheckAttByStudentRowItem;
import com.example.sarvesh.i_turnout.CheckAttStudentCustomAdapter;
import com.example.sarvesh.i_turnout.R;

import java.util.ArrayList;
import java.util.List;

public class SearchCustomAdapter extends BaseAdapter {

    Context context;
    List<SearchRowItem> rowItems;

    public SearchCustomAdapter(Context context, List<SearchRowItem> rowItems) {
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
    private static class ViewHolder {

        TextView studentname;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SearchCustomAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.searchitemlist, null);
            holder = new SearchCustomAdapter.ViewHolder();

            holder.studentname =  convertView
                    .findViewById(R.id.msearchitemlist1);


            SearchRowItem row_pos = rowItems.get(position);


            holder.studentname.setText(row_pos.getStudentname());


            convertView.setTag(holder);
        } else {
            holder = (SearchCustomAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
