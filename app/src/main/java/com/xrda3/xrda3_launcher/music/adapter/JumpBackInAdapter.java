package com.xrda3.xrda3_launcher.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.model.JumpBackInModel;

import java.util.ArrayList;

public class JumpBackInAdapter extends RecyclerView.Adapter<JumpBackInAdapter.ViewHolder> {

    Context context;
    ArrayList<JumpBackInModel> list;
    ImageView imgmid;
    ArrayList<JumpBackInModel> fullList;

    public JumpBackInAdapter(Context context, ArrayList<JumpBackInModel> list) {
        this.context = context;
        this.list = list;
        this.fullList = new ArrayList<>(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_jump_back_in, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JumpBackInModel item = list.get(position); // get current item

        holder.title.setText(item.getTitle());      // set text
        holder.imagesRes.setImageResource(item.getImageRes()); // set image
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filter(String text) {
        list.clear();
        if (text.isEmpty()) {
            list.addAll(fullList);
        } else {
            for (JumpBackInModel item : fullList) {
                if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    list.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imagesRes;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jumpTitle);
            imagesRes = itemView.findViewById(R.id.imgmid);
        }
    }
}
