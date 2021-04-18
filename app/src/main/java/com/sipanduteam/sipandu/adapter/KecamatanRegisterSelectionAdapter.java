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
import com.sipanduteam.sipandu.model.Kecamatan;

import java.util.ArrayList;
import java.util.List;

public class KecamatanRegisterSelectionAdapter extends ArrayAdapter<Kecamatan> {
    private List<Kecamatan> kecamatanList = new ArrayList<Kecamatan>();

    public KecamatanRegisterSelectionAdapter(@NonNull Context context, @NonNull List<Kecamatan> kecamatanList) {
        super(context, 0, kecamatanList);
        this.kecamatanList = kecamatanList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.login_role_dropdown_menu_item, parent, false
            );
        }
        TextView kecamatanName = convertView.findViewById(R.id.auto_complete_text);
        kecamatanName.setText(kecamatanList.get(position).getNamaKecamatan());
        return convertView;
    }
    @Override
    public int getCount() {
        return kecamatanList.size();
    }

    @Nullable
    @Override
    public Kecamatan getItem(int position) {
        return kecamatanList.get(position);
    }
}
