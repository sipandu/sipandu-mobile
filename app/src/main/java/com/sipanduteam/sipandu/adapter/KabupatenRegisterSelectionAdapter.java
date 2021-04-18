package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.Kabupaten;

import java.util.ArrayList;
import java.util.List;

public class KabupatenRegisterSelectionAdapter extends ArrayAdapter<Kabupaten> {
    private List<Kabupaten> kabupatenList = new ArrayList<Kabupaten>();

    public KabupatenRegisterSelectionAdapter(@NonNull Context context, @NonNull List<Kabupaten> kabupatenList) {
        super(context, 0, kabupatenList);
        this.kabupatenList = kabupatenList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.login_role_dropdown_menu_item, parent, false
            );
        }
        TextView kabupatenName = convertView.findViewById(R.id.auto_complete_text);
        kabupatenName.setText(kabupatenList.get(position).getNamaKabupaten());
        return convertView;
    }
    @Override
    public int getCount() {
        return kabupatenList.size();
    }

    @Nullable
    @Override
    public Kabupaten getItem(int position) {
        return kabupatenList.get(position);
    }
}
