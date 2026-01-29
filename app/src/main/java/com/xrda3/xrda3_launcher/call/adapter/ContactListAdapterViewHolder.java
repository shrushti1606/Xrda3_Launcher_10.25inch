package com.xrda3.xrda3_launcher.call.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;

public class ContactListAdapterViewHolder extends RecyclerView.ViewHolder {

    private TextView conName;
    private TextView conNumber;

    public ContactListAdapterViewHolder(@NonNull View itemView) {
        super(itemView);

        conName = itemView.findViewById(R.id.conName);
        conNumber = itemView.findViewById(R.id.conNumber);
    }

    public TextView getcon_name() {
        return conName;
    }

    public TextView getcon_number() {
        return conNumber;
    }
}
