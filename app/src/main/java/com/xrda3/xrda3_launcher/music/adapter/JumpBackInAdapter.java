package com.xrda3.xrda3_launcher.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.model.JumpBackInModel;

import java.util.ArrayList;

public class JumpBackInAdapter extends RecyclerView.Adapter<JumpBackInAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<JumpBackInModel> originalList;
    private final ArrayList<JumpBackInModel> filteredList;

    public JumpBackInAdapter(Context context, ArrayList<JumpBackInModel> list) {
        this.context = context;
        this.originalList = new ArrayList<>(list);
        this.filteredList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_jump_back_in, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JumpBackInModel model = filteredList.get(position);
        holder.title.setText(model.getTitle());
        holder.image.setImageResource(model.getImageRes());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String text) {
        filteredList.clear();

        if (text == null || text.trim().isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            String query = text.toLowerCase().trim();
            for (JumpBackInModel item : originalList) {
                if (item.getTitle().toLowerCase().contains(query)) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jumpTitle);
            image = itemView.findViewById(R.id.jumpImage);
        }
    }
}
