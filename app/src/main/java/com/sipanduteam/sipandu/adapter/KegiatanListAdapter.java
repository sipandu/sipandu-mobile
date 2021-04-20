package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.posyandu.KegiatanDetailActivity;
import com.sipanduteam.sipandu.model.Informasi;
import com.sipanduteam.sipandu.model.Kegiatan;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        try {
            if (format.parse(kegiatanArrayList.get(position).getEndAt()).after(new Date())) {
                if (format.parse(kegiatanArrayList.get(position).getStartAt()).after(new Date())) {
                    holder.kegiatanStatusText.setText("Belum terlaksana");
                    holder.kegiatanStatus.setCardBackgroundColor(
                            mContext.getResources().getColor(R.color.secondaryColor));
                }
                else {
                    holder.kegiatanStatusText.setText("Sedang berjalan");
                    holder.kegiatanStatus.setCardBackgroundColor(
                            mContext.getResources().getColor(R.color.primaryLightColor));
                }
            }
            else {
                holder.kegiatanStatusText.setText("Sudah selesai");
                holder.kegiatanStatus.setCardBackgroundColor(
                        mContext.getResources().getColor(R.color.green));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.kegiatanWaktu.setText((kegiatanArrayList.get(position).getStartAt() + " hingga " + kegiatanArrayList.get(position).getEndAt()));
    }

    @Override
    public int getItemCount() {
        return kegiatanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView kegiatanTitle, kegiatanTempat, kegiatanStatusText, kegiatanWaktu;
        private final Button kegiatanDetailButton;
        private final MaterialCardView kegiatanStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kegiatanTitle = itemView.findViewById(R.id.kegiatan_judul_text);
            kegiatanTempat = itemView.findViewById(R.id.kegiatan_lokasi_text);
            kegiatanStatusText = itemView.findViewById(R.id.kegiatan_status_text);
            kegiatanWaktu = itemView.findViewById(R.id.kegiatan_waktu_text);
            kegiatanStatus = itemView.findViewById(R.id.kegiatan_status);
            kegiatanDetailButton = itemView.findViewById(R.id.kegiatan_detail_button);
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
}
