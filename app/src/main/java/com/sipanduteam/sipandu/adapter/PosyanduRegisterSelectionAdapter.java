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
import com.sipanduteam.sipandu.model.posyandu.Posyandu;

import java.util.ArrayList;
import java.util.List;

public class PosyanduRegisterSelectionAdapter extends ArrayAdapter<Posyandu> {
    private List<Posyandu> posyanduList = new ArrayList<Posyandu>();

    public PosyanduRegisterSelectionAdapter(@NonNull Context context, @NonNull List<Posyandu> posyanduList) {
        super(context, 0, posyanduList);
        this.posyanduList = posyanduList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.login_role_dropdown_menu_item, parent, false
            );
        }
        TextView posyanduName = convertView.findViewById(R.id.auto_complete_text);
        posyanduName.setText(posyanduList.get(position).getBanjar() + " (" + posyanduList.get(position).getNamaPosyandu() + ")");
        return convertView;
    }


    @Override
    public int getCount() {
        return posyanduList.size();
    }

    @Nullable
    @Override
    public Posyandu getItem(int position) {
        return posyanduList.get(position);
    }
}
