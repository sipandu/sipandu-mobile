package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.posyandu.KegiatanDetailActivity;
import com.sipanduteam.sipandu.model.Anak;
import com.sipanduteam.sipandu.model.posyandu.Kegiatan;
import com.sipanduteam.sipandu.model.user.UserWithAnak;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class KeluargaAnakListAdapter extends RecyclerView.Adapter<KeluargaAnakListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<UserWithAnak> anakArrayList;
    private int position;
    String kegiatanKey = "KEGIATAN_ID";
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public KeluargaAnakListAdapter(Context context, ArrayList<UserWithAnak> anakArrayList) {
        mContext = context;
        this.anakArrayList = anakArrayList;
    }

    @NonNull
    @Override
    public KeluargaAnakListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.keluarga_list_card_layout, parent, false);
        return new KeluargaAnakListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeluargaAnakListAdapter.ViewHolder holder, int position) {
        //TODO pastiin url image nya nanti waktu production
        // replaceAll("http://192.168.1.3:1107", "https://sipandu-test-web.herokuapp.com")
        holder.progressContainer.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(anakArrayList.get(position).getProfileImage())
                .into(holder.anakImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressContainer.setVisibility(View.GONE);
                        holder.anakImage.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        holder.progressContainer.setVisibility(View.GONE);
//                        holder.imageFailedLoad.setVisibility(View.VISIBLE);
                    }
                });
        holder.namaAnak.setText(anakArrayList.get(position).getAnak().getNamaAnak());
        holder.emailAnak.setText(anakArrayList.get(position).getEmail());
    }


    @Override
    public int getItemCount() {
        return anakArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView namaAnak, emailAnak;
        private final ImageView anakImage;
        private final LinearLayout progressContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaAnak = itemView.findViewById(R.id.profile_name_text);
            emailAnak = itemView.findViewById(R.id.profile_email_text);
            progressContainer = itemView.findViewById(R.id.profile_image_loading_container);
            anakImage = itemView.findViewById(R.id.profile_image);
        }
    }
}
