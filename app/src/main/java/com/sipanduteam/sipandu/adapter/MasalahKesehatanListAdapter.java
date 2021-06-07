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
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import java.util.ArrayList;

public class MasalahKesehatanListAdapter extends RecyclerView.Adapter<MasalahKesehatanListAdapter.ViewHolder> {
    private Context mContext;
    private final ChangeDateFormat changeDateFormat = new ChangeDateFormat();
    private ArrayList<MasalahKesehatanLansia> masalahKesehatanLansiaArrayList;
    private int position;

    public MasalahKesehatanListAdapter(Context context, ArrayList<MasalahKesehatanLansia> masalahKesehatanLansiaArrayList) {
        mContext = context;
        this.masalahKesehatanLansiaArrayList = masalahKesehatanLansiaArrayList;
    }

    @NonNull
    @Override
    public MasalahKesehatanListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.masalah_kesehatan_card_layout, parent, false);
        return new MasalahKesehatanListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MasalahKesehatanListAdapter.ViewHolder holder, int position) {
        holder.masalahKesehatan.setText(masalahKesehatanLansiaArrayList.get(position).getNamaPenyakit());

        if (masalahKesehatanLansiaArrayList.get(position).getStatus().toString().equals("Sudah Sembuh")) {
            holder.statusChip.setText("Sudah sembuh");
            holder.statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.greenSemiTransparent)));
        }

        else if (masalahKesehatanLansiaArrayList.get(position).getStatus().toString().equals("Sedang Mengalami")) {
            holder.statusChip.setText("Sedang mengalami");
            holder.statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.secondaryLightColorSemiTransparent)));
        }
        holder.tanggalDitambahkan.setText(changeDateFormat.changeDateFormat(masalahKesehatanLansiaArrayList.get(position).getCreatedAt()));
    }


    @Override
    public int getItemCount() {
        return masalahKesehatanLansiaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView masalahKesehatan, tanggalDitambahkan;
        private final Chip statusChip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            masalahKesehatan = itemView.findViewById(R.id.masalah_kesehatan_text);
            tanggalDitambahkan = itemView.findViewById(R.id.masalah_kesehatan_tanggal_text);
            statusChip = itemView.findViewById(R.id.masalah_kesehatan_status);
        }
    }
}
