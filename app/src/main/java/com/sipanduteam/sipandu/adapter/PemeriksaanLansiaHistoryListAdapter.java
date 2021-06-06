package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.bumil.DetailRiwayatPemeriksaanIbuActivity;
import com.sipanduteam.sipandu.activity.lansia.DetailRiwayatPemeriksaanLansiaActivity;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbu;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanLansia;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import java.util.ArrayList;

public class PemeriksaanLansiaHistoryListAdapter extends RecyclerView.Adapter<PemeriksaanLansiaHistoryListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RiwayatPemeriksaanLansia> riwayatPemeriksaanLansiaArrayList;
    private int position;
    private final ChangeDateFormat changeDateFormat = new ChangeDateFormat();

    public PemeriksaanLansiaHistoryListAdapter(Context context, ArrayList<RiwayatPemeriksaanLansia> riwayatPemeriksaanLansiaArrayList) {
        mContext = context;
        this.riwayatPemeriksaanLansiaArrayList = riwayatPemeriksaanLansiaArrayList;
    }

    @NonNull
    @Override
    public PemeriksaanLansiaHistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.riwayat_pemeriksaan_lansia_card_layout, parent, false);
        return new PemeriksaanLansiaHistoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemeriksaanLansiaHistoryListAdapter.ViewHolder holder, int position) {
        holder.tanggalPemeriksaan.setText("Pemeriksaan tanggal " + changeDateFormat.changeDateFormat(riwayatPemeriksaanLansiaArrayList.get(position).getTanggalPemeriksaan()));
        holder.namaPemeriksa.setText(riwayatPemeriksaanLansiaArrayList.get(position).getNamaPemeriksa());
        holder.tanggalKembali.setText(changeDateFormat.changeDateFormat(riwayatPemeriksaanLansiaArrayList.get(position).getTanggalKembali()));
        holder.imtLansia.setText("IMT: " + riwayatPemeriksaanLansiaArrayList.get(position).getImt().toString());
    }

    @Override
    public int getItemCount() {
        return riwayatPemeriksaanLansiaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView tanggalPemeriksaan, tanggalKembali, namaPemeriksa;
        private final Chip imtLansia;
        private final Button detailPemeriksaan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggalPemeriksaan = itemView.findViewById(R.id.pemeriksaan_date_text);
            tanggalKembali = itemView.findViewById(R.id.tanggal_kembali_text);
            namaPemeriksa = itemView.findViewById(R.id.nama_pemeriksa_text);
            imtLansia = itemView.findViewById(R.id.imt_lansia_chip);
            detailPemeriksaan = itemView.findViewById(R.id.pemeriksaan_detail_button);
            detailPemeriksaan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getAdapterPosition();
                    RiwayatPemeriksaanLansia riwayatPemeriksaanLansia = riwayatPemeriksaanLansiaArrayList.get(position);
                    Intent detailPemeriksaan = new Intent(mContext, DetailRiwayatPemeriksaanLansiaActivity.class);
                    Gson gson = new Gson();
                    String pemeriksaanJson = gson.toJson(riwayatPemeriksaanLansia);
                    detailPemeriksaan.putExtra("DATAPEMERIKSAAN", pemeriksaanJson);
                    mContext.startActivity(detailPemeriksaan);
                }
            });
        }
    }
}
