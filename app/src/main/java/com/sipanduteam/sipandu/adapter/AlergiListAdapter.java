package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.Alergi;
import com.sipanduteam.sipandu.model.MasalahKesehatanLansia;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import java.util.ArrayList;

public class AlergiListAdapter extends RecyclerView.Adapter<AlergiListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Alergi> alergiArrayList;
    private int position;

    public AlergiListAdapter(Context context, ArrayList<Alergi> alergiArrayList) {
        mContext = context;
        this.alergiArrayList = alergiArrayList;
    }

    @NonNull
    @Override
    public AlergiListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.alergi_card_layout, parent, false);
        return new AlergiListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlergiListAdapter.ViewHolder holder, int position) {
        holder.namaAlergi.setText(alergiArrayList.get(position).getNamaAlergi());
        holder.kategoriAlergi.setText(alergiArrayList.get(position).getKategori());
    }


    @Override
    public int getItemCount() {
        return alergiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView namaAlergi, kategoriAlergi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaAlergi = itemView.findViewById(R.id.alergi_nama_text);
            kategoriAlergi = itemView.findViewById(R.id.kategori_alergi_text);
        }
    }
}
