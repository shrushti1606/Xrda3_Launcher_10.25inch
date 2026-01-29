package com.xrda3.xrda3_launcher.call.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.DialedActivity;
import com.xrda3.xrda3_launcher.call.model.HomeCallModel;

import java.util.ArrayList;
import java.util.List;

public class HomeCallAdapter extends BaseAdapter {

    private Context context;
    private List<HomeCallModel> callList;
    private List<HomeCallModel> fullList;

    public HomeCallAdapter(Context context, List<HomeCallModel> callList) {
        this.context = context;
        this.callList = new ArrayList<>(callList);
        this.fullList = new ArrayList<>(callList);
    }

    @Override
    public int getCount() { return callList.size(); }

    @Override
    public Object getItem(int position) { return callList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    static class ViewHolder {
        TextView conName, conInfo;
        ImageView btnCall;
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
            holder.btnCall = convertView.findViewById(R.id.btnCall);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HomeCallModel model = callList.get(position);

        holder.conName.setText(model.getName());
        holder.conInfo.setText(model.getInfo());

        holder.btnCall.setOnClickListener(v -> {
            String phone = model.getPhone();
            if (phone.length() != 10 || !phone.matches("\\d{10}")) {
                Toast.makeText(context, "Number must be 10 digits", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(context, DialedActivity.class);
            intent.putExtra("name", model.getName());
            intent.putExtra("phone", phone);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        return convertView;
    }

    public void filterCalls(int type) {
        callList.clear();
        if (type == -1) {
            callList.addAll(fullList);
        } else {
            for (HomeCallModel call : fullList) {
                if (call.getType() == type) {
                    callList.add(call);
                }
            }
        }
        notifyDataSetChanged();
    }
}
