package com.xrda3.xrda3_launcher.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.model.JumpBackInModel;

import java.util.ArrayList;
import java.util.List;

public class JumpBackInAdapter
        extends RecyclerView.Adapter<JumpBackInAdapter.ViewHolder> {

    private final Context context;
    private final List<JumpBackInModel> originalList;
    private final List<JumpBackInModel> filteredList;
    private final OnJumpBackClickListener listener;

    public JumpBackInAdapter(Context context,
                             List<JumpBackInModel> list,
                             OnJumpBackClickListener listener) {
        this.context = context;
        this.originalList = new ArrayList<>(list);
        this.filteredList = new ArrayList<>(list);
        this.listener = listener;
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

        JumpBackInModel item = filteredList.get(position);

        holder.jumpTitle.setText(item.getTitle());
        holder.jumpImage.setImageResource(item.getImageRes());

        holder.cardView.setOnClickListener(v -> {
            if (listener != null) listener.onCardClick(item);
        });

        holder.jumpImage.setOnClickListener(v -> {
            if (listener != null) listener.onImageClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String query) {
        filteredList.clear();

        if (query == null || query.trim().isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            String search = query.toLowerCase().trim();
            for (JumpBackInModel item : originalList) {
                if (item.getTitle().toLowerCase().contains(search)) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView jumpImage;
        TextView jumpTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            jumpImage = itemView.findViewById(R.id.jumpImage);
            jumpTitle = itemView.findViewById(R.id.jumpTitle);
        }
    }
}
