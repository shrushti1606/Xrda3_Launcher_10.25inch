package com.xrda3.xrda3_launcher.music.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.model.FavCategoryModel;

import java.util.List;

public class FavCategoryAdapter extends RecyclerView.Adapter<FavCategoryAdapter.ViewHolder> {

    private List<FavCategoryModel> list;

    public FavCategoryAdapter(List<FavCategoryModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavCategoryModel model = list.get(position);
        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.songCategory);
            desc = itemView.findViewById(R.id.songDesc);
        }
    }
}
