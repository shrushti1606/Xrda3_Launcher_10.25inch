package com.xrda3.xrda3_launcher.call.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.DialedActivity;
import com.xrda3.xrda3_launcher.call.model.FavouriteCallModel;

import java.util.ArrayList;

public class FavouriteCallAdapter {

    private Context context;
    private LinearLayout container;
    private ArrayList<FavouriteCallModel> list;

    public FavouriteCallAdapter(Context context,
                            LinearLayout container,
                            ArrayList<FavouriteCallModel> list) {
        this.context = context;
        this.container = container;
        this.list = list;
    }

    public void refresh() {
        container.removeAllViews();

        for (FavouriteCallModel model : list) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_favorite_contact, container, false);

            TextView conName = view.findViewById(R.id.conName);
            TextView conType = view.findViewById(R.id.conType);
            ImageButton btnCall = view.findViewById(R.id.btnCall);

            conName.setText(model.getName());
            conType.setText(model.getType());

            btnCall.setOnClickListener(v -> {
                Intent intent = new Intent(context, DialedActivity.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("phone", model.getPhone());
                context.startActivity(intent);
            });

            view.setOnLongClickListener(v -> {
                list.remove(model);
                refresh();
                return true;
            });

            container.addView(view);
        }
    }
}

