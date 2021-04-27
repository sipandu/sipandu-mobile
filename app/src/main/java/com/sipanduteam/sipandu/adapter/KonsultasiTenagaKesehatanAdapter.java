package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.posyandu.Pegawai;

import java.util.ArrayList;

public class KonsultasiTenagaKesehatanAdapter extends RecyclerView.Adapter<KonsultasiTenagaKesehatanAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<Pegawai> pegawaiArrayList;
    private int position;

    public KonsultasiTenagaKesehatanAdapter(Context context, ArrayList<Pegawai> pegawaiArrayList) {
        mContext = context;
        this.pegawaiArrayList = pegawaiArrayList;
    }

    @NonNull
    @Override
    public KonsultasiTenagaKesehatanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tenaga_kesehatan_konsultasi_card_layout, parent, false);
        return new KonsultasiTenagaKesehatanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KonsultasiTenagaKesehatanAdapter.ViewHolder holder, int position) {
        holder.pegawaiName.setText(pegawaiArrayList.get(position).getNamaPegawai());
        holder.pegawaiTelegram.setText(pegawaiArrayList.get(position).getUsernameTelegram().replaceAll("@", ""));
        if (pegawaiArrayList.get(position).getStatus().equals("tersedia")) {
            holder.statusChip.setText("Tersedia");
            holder.statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.greenSemiTransparent)));
        }
        else {
            holder.statusChip.setText("Tidak tersedia");
            holder.statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.secondaryLightColorSemiTransparent)));
            holder.openTelegram.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return pegawaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView pegawaiName, pegawaiTelegram;
        private final Button openTelegram;
        private final Chip statusChip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pegawaiName = itemView.findViewById(R.id._konsultasi_tenaga_kesehatan_nama);
            statusChip = itemView.findViewById(R.id.konsultasi_status);
            pegawaiTelegram = itemView.findViewById(R.id.telegram_id_text);
            openTelegram = itemView.findViewById(R.id.konsultasi_telegram_button);
            openTelegram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://t.me/" + pegawaiArrayList.get(getAdapterPosition()).getUsernameTelegram().replaceAll("@", "");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    mContext.startActivity(i);
                }
            });
        }
    }
}
