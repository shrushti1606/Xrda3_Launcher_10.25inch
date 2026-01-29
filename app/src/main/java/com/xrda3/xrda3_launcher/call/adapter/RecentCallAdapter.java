package com.xrda3.xrda3_launcher.call.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.model.RecentCallModel;

import java.util.ArrayList;

public class RecentCallAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RecentCallModel> callList;

    public RecentCallAdapter(Context context, ArrayList<RecentCallModel> callList) {
        this.context = context;
        this.callList = callList;
    }

    @Override
    public int getCount() { return callList.size(); }

    @Override
    public Object getItem(int position) { return callList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    static class ViewHolder {
        TextView conName, conInfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_recent_calls, parent, false);
            holder = new ViewHolder();
            holder.conName = convertView.findViewById(R.id.conName);
            holder.conInfo = convertView.findViewById(R.id.conInfo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RecentCallModel model = callList.get(position);
        holder.conName.setText(model.getName());
        holder.conInfo.setText(model.getInfo());

        return convertView;
    }
}
