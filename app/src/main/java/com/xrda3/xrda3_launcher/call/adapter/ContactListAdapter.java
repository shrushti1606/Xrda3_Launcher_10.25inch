package com.xrda3.xrda3_launcher.call.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.ContactListActivity;

public class ContactListAdapter
        extends RecyclerView.Adapter<ContactListAdapterViewHolder> {

    String[] contactlist = {
            "dummy 1",
            "dummy 2",
            "dummy 3",
            "dummy 4",
            "dummy 5",
            "dummy 6",
            "dummy 7"
    };

    public ContactListAdapter(ContactListActivity contactListActivity) {
    }

    @NonNull
    @Override
    public ContactListAdapterViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapterViewHolder holder, int position) {

        if (holder.getcon_number() != null) {
            holder.getcon_number().setText(String.valueOf(position + 1));
        }

        if (holder.getcon_name() != null) {
            holder.getcon_name().setText(contactlist[position]);
        }
    }


    @Override
    public int getItemCount() {
        return contactlist.length;
    }
}
