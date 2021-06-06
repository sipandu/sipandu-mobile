package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.anak.DetailRiwayatPemeriksaanAnakActivity;
import com.sipanduteam.sipandu.activity.bumil.DetailRiwayatPemeriksaanIbuActivity;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanAnak;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbu;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import java.util.ArrayList;

public class PemeriksaanIbuHistoryListAdapter extends RecyclerView.Adapter<PemeriksaanIbuHistoryListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RiwayatPemeriksaanIbu> riwayatPemeriksaanIbuArrayList;
    private int position;
    private final ChangeDateFormat changeDateFormat = new ChangeDateFormat();

    public PemeriksaanIbuHistoryListAdapter(Context context, ArrayList<RiwayatPemeriksaanIbu> riwayatPemeriksaanIbuArrayList) {
        mContext = context;
        this.riwayatPemeriksaanIbuArrayList = riwayatPemeriksaanIbuArrayList;
    }

    @NonNull
    @Override
    public PemeriksaanIbuHistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.riwayat_pemeriksaan_ibu_card_layout, parent, false);
        return new PemeriksaanIbuHistoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemeriksaanIbuHistoryListAdapter.ViewHolder holder, int position) {
        holder.tanggalPemeriksaan.setText("Pemeriksaan tanggal " + changeDateFormat.changeDateFormat(riwayatPemeriksaanIbuArrayList.get(position).getTanggalPemeriksaan()));
        holder.namaPemeriksa.setText(riwayatPemeriksaanIbuArrayList.get(position).getNamaPemeriksa());
        holder.tanggalKembali.setText(changeDateFormat.changeDateFormat(riwayatPemeriksaanIbuArrayList.get(position).getTanggalKembali()));
        holder.usiaKandungan.setText(riwayatPemeriksaanIbuArrayList.get(position).getUsiaKandungan() + " minggu");
    }

    @Override
    public int getItemCount() {
        return riwayatPemeriksaanIbuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView tanggalPemeriksaan, tanggalKembali, namaPemeriksa, usiaKandungan;
        private final Button detailPemeriksaan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggalPemeriksaan = itemView.findViewById(R.id.pemeriksaan_date_text);
            tanggalKembali = itemView.findViewById(R.id.tanggal_kembali_text);
            namaPemeriksa = itemView.findViewById(R.id.nama_pemeriksa_text);
            usiaKandungan = itemView.findViewById(R.id.umur_kehamilan_text);
            detailPemeriksaan = itemView.findViewById(R.id.pemeriksaan_detail_button);
            detailPemeriksaan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getAdapterPosition();
                    RiwayatPemeriksaanIbu riwayatPemeriksaanIbu = riwayatPemeriksaanIbuArrayList.get(position);
                    Intent detailPemeriksaan = new Intent(mContext, DetailRiwayatPemeriksaanIbuActivity.class);
                    Gson gson = new Gson();
                    String pemeriksaanJson = gson.toJson(riwayatPemeriksaanIbu);
                    detailPemeriksaan.putExtra("DATAPEMERIKSAAN", pemeriksaanJson);
                    mContext.startActivity(detailPemeriksaan);
                }
            });
        }
    }
}
