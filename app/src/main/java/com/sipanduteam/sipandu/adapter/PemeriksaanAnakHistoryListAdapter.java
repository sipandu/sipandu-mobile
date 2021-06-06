package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.anak.DetailRiwayatPemeriksaanAnakActivity;
import com.sipanduteam.sipandu.activity.posyandu.KegiatanDetailActivity;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanAnak;
import com.sipanduteam.sipandu.model.posyandu.Kegiatan;
import com.sipanduteam.sipandu.model.vitamin.RiwayatVitamin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PemeriksaanAnakHistoryListAdapter extends RecyclerView.Adapter<PemeriksaanAnakHistoryListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RiwayatPemeriksaanAnak> riwayatPemeriksaanAnakArrayList;
    private int position;

    public PemeriksaanAnakHistoryListAdapter(Context context, ArrayList<RiwayatPemeriksaanAnak> riwayatPemeriksaanAnakArrayList) {
        mContext = context;
        this.riwayatPemeriksaanAnakArrayList = riwayatPemeriksaanAnakArrayList;
    }

    @NonNull
    @Override
    public PemeriksaanAnakHistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.riwayat_pemeriksaan_anak_card_layout, parent, false);
        return new PemeriksaanAnakHistoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemeriksaanAnakHistoryListAdapter.ViewHolder holder, int position) {
        holder.tanggalPemeriksaan.setText("Pemeriksaan tanggal " + changeDateFormat(riwayatPemeriksaanAnakArrayList.get(position).getTanggalPemeriksaan()));
        holder.namaPemeriksa.setText(riwayatPemeriksaanAnakArrayList.get(position).getNamaPemeriksa());
        holder.tanggalKembali.setText(changeDateFormat(riwayatPemeriksaanAnakArrayList.get(position).getTanggalKembali()));
        if (riwayatPemeriksaanAnakArrayList.get(position).getStatusGizi().toString().equals("Cukup Gizi")) {
            holder.statusGizi.setText("Cukup gizi");
            holder.statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.greenSemiTransparent)));
        }
        else if (riwayatPemeriksaanAnakArrayList.get(position).getStatusGizi().toString().equals("Kurang Gizi")) {
            holder.statusGizi.setText("Kurang gizi");
            holder.statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.warning)));
        }
        else if (riwayatPemeriksaanAnakArrayList.get(position).getStatusGizi().toString().equals("Kelebihan Gizi")) {
            holder.statusGizi.setText("Kelebihan gizi");
            holder.statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.secondaryDarkColorTranslucent)));
        }
    }


    @Override
    public int getItemCount() {
        return riwayatPemeriksaanAnakArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView tanggalPemeriksaan, tanggalKembali, namaPemeriksa;
        private final Chip statusGizi;
        private final Button detailPemeriksaan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggalPemeriksaan = itemView.findViewById(R.id.pemeriksaan_date_text);
            tanggalKembali = itemView.findViewById(R.id.tanggal_kembali_text);
            namaPemeriksa = itemView.findViewById(R.id.nama_pemeriksa_text);
            statusGizi = itemView.findViewById(R.id.status_gizi_anak_chip);
            detailPemeriksaan = itemView.findViewById(R.id.pemeriksaan_detail_button);
            detailPemeriksaan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getAdapterPosition();
                    RiwayatPemeriksaanAnak riwayatPemeriksaanAnak = riwayatPemeriksaanAnakArrayList.get(position);
                    Intent detailPemeriksaan = new Intent(mContext, DetailRiwayatPemeriksaanAnakActivity.class);
                    Gson gson = new Gson();
                    String pemeriksaanJson = gson.toJson(riwayatPemeriksaanAnak);
                    detailPemeriksaan.putExtra("DATAPEMERIKSAAN", pemeriksaanJson);
                    mContext.startActivity(detailPemeriksaan);
                }
            });

        }
    }

    public String changeDateFormat(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MMMM-yyyy";
        SimpleDateFormat input = new SimpleDateFormat(inputPattern);
        SimpleDateFormat output = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = input.parse(time);
            str = output.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}

