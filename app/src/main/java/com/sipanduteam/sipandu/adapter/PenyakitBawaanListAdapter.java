package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.MasalahKesehatanLansia;
import com.sipanduteam.sipandu.model.PenyakitBawaan;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import java.util.ArrayList;

public class PenyakitBawaanListAdapter extends RecyclerView.Adapter<PenyakitBawaanListAdapter.ViewHolder> {
    private Context mContext;
    private final ChangeDateFormat changeDateFormat = new ChangeDateFormat();
    private ArrayList<PenyakitBawaan> penyakitBawaanArrayList;
    private int position;

    public PenyakitBawaanListAdapter(Context context, ArrayList<PenyakitBawaan> penyakitBawaanArrayList) {
        mContext = context;
        this.penyakitBawaanArrayList = penyakitBawaanArrayList;
    }

    @NonNull
    @Override
    public PenyakitBawaanListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.penyakit_bawaan_card_layout, parent, false);
        return new PenyakitBawaanListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenyakitBawaanListAdapter.ViewHolder holder, int position) {
        holder.penyakitBawaan.setText(penyakitBawaanArrayList.get(position).getNamaPenyakit());
        holder.tanggalDitambahkan.setText(changeDateFormat.changeDateFormat(penyakitBawaanArrayList.get(position).getCreatedAt()));
    }


    @Override
    public int getItemCount() {
        return penyakitBawaanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView penyakitBawaan, tanggalDitambahkan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            penyakitBawaan = itemView.findViewById(R.id.penyakit_bawaan_text);
            tanggalDitambahkan = itemView.findViewById(R.id.penyakit_bawaan_tanggal_text);
        }
    }
}
