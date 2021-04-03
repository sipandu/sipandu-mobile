package com.sipanduteam.sipandu.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.Desa;
import com.sipanduteam.sipandu.model.Kabupaten;

import java.util.ArrayList;
import java.util.List;

public class DesaRegisterSelectionAdapter extends ArrayAdapter<Desa> {
    private List<Desa> desaList = new ArrayList<Desa>();

    public DesaRegisterSelectionAdapter(@NonNull Context context, @NonNull List<Desa> desaList) {
        super(context, 0, desaList);
        this.desaList = desaList;
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
        kabupatenName.setText(desaList.get(position).getNamaDesa());
        return convertView;
    }
}
