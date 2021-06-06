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
import com.sipanduteam.sipandu.activity.posyandu.KegiatanDetailActivity;
import com.sipanduteam.sipandu.model.posyandu.Kegiatan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class KegiatanListAdapter extends RecyclerView.Adapter<KegiatanListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Kegiatan> kegiatanArrayList;
    private int position;
    String kegiatanKey = "KEGIATAN_ID";
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public KegiatanListAdapter(Context context, ArrayList<Kegiatan> kegiatanArrayList) {
        mContext = context;
        this.kegiatanArrayList = kegiatanArrayList;
    }

    @NonNull
    @Override
    public KegiatanListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.kegiatan_card_layout, parent, false);
        return new KegiatanListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KegiatanListAdapter.ViewHolder holder, int position) {
        holder.kegiatanTitle.setText(kegiatanArrayList.get(position).getNamaKegiatan());
        holder.kegiatanTempat.setText(kegiatanArrayList.get(position).getTempat());

        switch (kegiatanArrayList.get(position).getStatus()){
            case 0:
                holder.statusChip.setText("Belum berjalan");
                holder.statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.secondaryLightColorSemiTransparent)));
                break;
            case 1:
                holder.statusChip.setText("Sedang berjalan");
                holder.statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.primaryLightColorSemiTransparent)));
                break;
            case 2:
                holder.statusChip.setText("Sudah selesai");
                holder.statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.greenSemiTransparent)));
                break;
        }
        holder.kegiatanWaktu.setText(changeDateFormat((kegiatanArrayList.get(position).getStartAt())) + " hingga " + changeDateFormat(kegiatanArrayList.get(position).getEndAt()));
    }


    @Override
    public int getItemCount() {
        return kegiatanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView kegiatanTitle, kegiatanTempat, kegiatanWaktu;
        private final Button kegiatanDetailButton;
        private final Chip statusChip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kegiatanTitle = itemView.findViewById(R.id.kegiatan_judul_text);
            kegiatanTempat = itemView.findViewById(R.id.kegiatan_lokasi_text);
            kegiatanWaktu = itemView.findViewById(R.id.kegiatan_waktu_text);
            kegiatanDetailButton = itemView.findViewById(R.id.kegiatan_detail_button);
            statusChip = itemView.findViewById(R.id.kegiatan_status1);
            kegiatanDetailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getAdapterPosition();
                    Kegiatan kegiatan = kegiatanArrayList.get(position);
                    Intent kegiatanDetail = new Intent(mContext, KegiatanDetailActivity.class);
                    Gson gson = new Gson();
                    String kegiatanJson = gson.toJson(kegiatan);
                    kegiatanDetail.putExtra(kegiatanKey, kegiatanJson);
                    mContext.startActivity(kegiatanDetail);
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
