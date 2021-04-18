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
import com.sipanduteam.sipandu.model.Desa;

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
        TextView desaName = convertView.findViewById(R.id.auto_complete_text);
        desaName.setText(desaList.get(position).getNamaDesa());
        return convertView;
    }
    @Override
    public int getCount() {
        return desaList.size();
    }

    @Nullable
    @Override
    public Desa getItem(int position) {
        return desaList.get(position);
    }
}
