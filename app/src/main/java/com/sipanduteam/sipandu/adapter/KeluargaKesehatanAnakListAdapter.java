package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.anak.KesehatanAnakActivity;
import com.sipanduteam.sipandu.model.user.UserWithAnak;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class KeluargaKesehatanAnakListAdapter extends RecyclerView.Adapter<KeluargaKesehatanAnakListAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<UserWithAnak> anakArrayList;
    private int position;
    String kegiatanKey = "KEGIATAN_ID";
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public KeluargaKesehatanAnakListAdapter(Context context, ArrayList<UserWithAnak> anakArrayList) {
        mContext = context;
        this.anakArrayList = anakArrayList;
    }

    @NonNull
    @Override
    public KeluargaKesehatanAnakListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.kesehatan_keluarga_card_list, parent, false);
        return new KeluargaKesehatanAnakListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeluargaKesehatanAnakListAdapter.ViewHolder holder, int position) {
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
        private final Button dataKesehatanButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaAnak = itemView.findViewById(R.id.profile_name_text);
            emailAnak = itemView.findViewById(R.id.profile_email_text);
            progressContainer = itemView.findViewById(R.id.profile_image_loading_container);
            anakImage = itemView.findViewById(R.id.profile_image);
            dataKesehatanButton = itemView.findViewById(R.id.data_kesehatan_button);
            dataKesehatanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent kesehatanAnak = new Intent(mContext, KesehatanAnakActivity.class);
                    kesehatanAnak.putExtra("EMAIL", anakArrayList.get(getAdapterPosition()).getEmail());
                    mContext.startActivity(kesehatanAnak);
                }
            });
        }
    }
}
