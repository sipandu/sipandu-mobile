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
import com.sipanduteam.sipandu.activity.bumil.KesehatanIbuActivity;
import com.sipanduteam.sipandu.activity.lansia.KesehatanLansiaActivity;
import com.sipanduteam.sipandu.model.user.UserWithLansia;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KeluargaKesehatanLansiaListAdapter extends RecyclerView.Adapter<KeluargaKesehatanLansiaListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<UserWithLansia> lansiaArrayList;
    private int position;

    public KeluargaKesehatanLansiaListAdapter(Context context, ArrayList<UserWithLansia> lansiaArrayList) {
        mContext = context;
        this.lansiaArrayList = lansiaArrayList;
    }

    @NonNull
    @Override
    public KeluargaKesehatanLansiaListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.kesehatan_keluarga_card_list, parent, false);
        return new KeluargaKesehatanLansiaListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeluargaKesehatanLansiaListAdapter.ViewHolder holder, int position) {
        //TODO pastiin url image nya nanti waktu production
        // replaceAll("http://192.168.1.3:1107", "https://sipandu-test-web.herokuapp.com")
        holder.progressContainer.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(lansiaArrayList.get(position).getProfileImage())
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
        holder.namaLansia.setText(lansiaArrayList.get(position).getLansia().getNamaLansia());
        holder.emailLansia.setText(lansiaArrayList.get(position).getEmail());
    }


    @Override
    public int getItemCount() {
        return lansiaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView namaLansia, emailLansia;
        private final ImageView anakImage;
        private final LinearLayout progressContainer;
        private final Button dataKesehatanButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaLansia = itemView.findViewById(R.id.profile_name_text);
            emailLansia = itemView.findViewById(R.id.profile_email_text);
            progressContainer = itemView.findViewById(R.id.profile_image_loading_container);
            anakImage = itemView.findViewById(R.id.profile_image);
            dataKesehatanButton = itemView.findViewById(R.id.data_kesehatan_button);
            dataKesehatanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent kesehatanLansia = new Intent(mContext, KesehatanLansiaActivity.class);
                    kesehatanLansia.putExtra("EMAIL", lansiaArrayList.get(getAdapterPosition()).getEmail());
                    mContext.startActivity(kesehatanLansia);
                }
            });
        }
    }
}
