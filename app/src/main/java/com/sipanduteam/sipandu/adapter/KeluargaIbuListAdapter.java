package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.user.UserWithAnak;
import com.sipanduteam.sipandu.model.user.UserWithIbu;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class KeluargaIbuListAdapter extends RecyclerView.Adapter<KeluargaIbuListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<UserWithIbu> ibuArrayList;
    private int position;
    String kegiatanKey = "KEGIATAN_ID";
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public KeluargaIbuListAdapter(Context context, ArrayList<UserWithIbu> ibuArrayList) {
        mContext = context;
        this.ibuArrayList = ibuArrayList;
    }

    @NonNull
    @Override
    public KeluargaIbuListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.keluarga_list_card_layout, parent, false);
        return new KeluargaIbuListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeluargaIbuListAdapter.ViewHolder holder, int position) {
        //TODO pastiin url image nya nanti waktu production
        // replaceAll("http://192.168.1.3:1107", "https://sipandu-test-web.herokuapp.com")
        holder.progressContainer.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(ibuArrayList.get(position).getProfileImage())
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
        holder.namaIbu.setText(ibuArrayList.get(position).getIbu().getNamaIbuHamil());
        holder.emailIbu.setText(ibuArrayList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return ibuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView namaIbu, emailIbu;
        private final ImageView anakImage;
        private final LinearLayout progressContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaIbu = itemView.findViewById(R.id.profile_name_text);
            emailIbu = itemView.findViewById(R.id.profile_email_text);
            progressContainer = itemView.findViewById(R.id.profile_image_loading_container);
            anakImage = itemView.findViewById(R.id.profile_image);
        }
    }
}
