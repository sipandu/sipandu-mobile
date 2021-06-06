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
import com.sipanduteam.sipandu.model.vitamin.RiwayatVitamin;

import java.util.ArrayList;

public class VitaminHistoryListAdapter extends RecyclerView.Adapter<VitaminHistoryListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RiwayatVitamin> riwayatVitaminArrayList;
    private int position;

    public VitaminHistoryListAdapter(Context context, ArrayList<RiwayatVitamin> riwayatVitaminArrayList) {
        mContext = context;
        this.riwayatVitaminArrayList = riwayatVitaminArrayList;
    }

    @NonNull
    @Override
    public VitaminHistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.riwayat_vitamin_card_layout, parent, false);
        return new VitaminHistoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VitaminHistoryListAdapter.ViewHolder holder, int position) {
        holder.namaVitamin.setText(riwayatVitaminArrayList.get(position).getVitamin().getNamaVitamin());
        holder.keteranganVitamin.setText(riwayatVitaminArrayList.get(position).getVitamin().getDeskripsi());
        holder.tanggalPemberian.setText(riwayatVitaminArrayList.get(position).getTanggalPemberian());
        if (riwayatVitaminArrayList.get(position).getTanggalKembali() == null) {
            holder.pemberianSelanjutnya.setText("Tidak ada");
        }
        else {
            holder.pemberianSelanjutnya.setText(riwayatVitaminArrayList.get(position).getTanggalKembali().toString());
        }
        holder.lokasiPemberian.setText(riwayatVitaminArrayList.get(position).getLokasi());
        holder.usiaPemberian.setText(riwayatVitaminArrayList.get(position).getUsia().toString());
        holder.namaPemberi.setText(riwayatVitaminArrayList.get(position).getNamaPemeriksa());
        if (riwayatVitaminArrayList.get(position).getKeterangan() == null) {
            holder.keteranganPemberian.setText("Tidak ada");
        }
        else {
            holder.keteranganPemberian.setText(riwayatVitaminArrayList.get(position).getKeterangan().toString());
        }
    }


    @Override
    public int getItemCount() {
        return riwayatVitaminArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView namaVitamin, keteranganVitamin,
                tanggalPemberian, pemberianSelanjutnya, lokasiPemberian,
                usiaPemberian, namaPemberi, keteranganPemberian;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaVitamin = itemView.findViewById(R.id.vitamin_nama_text);
            keteranganVitamin = itemView.findViewById(R.id.vitamin_keterangan_text);
            tanggalPemberian = itemView.findViewById(R.id.vitamin_tanggal_text);
            pemberianSelanjutnya = itemView.findViewById(R.id.vitamin_tanggal_next_text);
            lokasiPemberian = itemView.findViewById(R.id.vitamin_lokasi_text);
            usiaPemberian = itemView.findViewById(R.id.vitamin_usia_text);
            namaPemberi = itemView.findViewById(R.id.nama_pemeriksa_vitamin_text);
            keteranganPemberian = itemView.findViewById(R.id.vitamin_keterangan_pemberian_text);

        }
    }
}
