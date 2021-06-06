package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.imunisasi.RiwayatImunisasi;
import com.sipanduteam.sipandu.model.vitamin.RiwayatVitamin;

import java.util.ArrayList;

public class ImunisasiHistoryListAdapter extends RecyclerView.Adapter<ImunisasiHistoryListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RiwayatImunisasi> riwayatImunisasiArrayList;
    private int position;

    public ImunisasiHistoryListAdapter(Context context, ArrayList<RiwayatImunisasi> riwayatImunisasiArrayList) {
        mContext = context;
        this.riwayatImunisasiArrayList = riwayatImunisasiArrayList;
    }

    @NonNull
    @Override
    public ImunisasiHistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.riwayat_imunisasi_card_layout, parent, false);
        return new ImunisasiHistoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImunisasiHistoryListAdapter.ViewHolder holder, int position) {
        holder.namaImunisasi.setText(riwayatImunisasiArrayList.get(position).getImunisasi().getNamaImunisasi());
        holder.keteranganImunisasi.setText(riwayatImunisasiArrayList.get(position).getImunisasi().getDeskripsi());
        holder.tanggalPemberian.setText(riwayatImunisasiArrayList.get(position).getTanggalImunisasi());
        if (riwayatImunisasiArrayList.get(position).getTanggalKembali() == null) {
            holder.pemberianSelanjutnya.setText("Tidak ada");
        }
        else {
            holder.pemberianSelanjutnya.setText(riwayatImunisasiArrayList.get(position).getTanggalKembali().toString());
        }
        holder.lokasiPemberian.setText(riwayatImunisasiArrayList.get(position).getLokasi());
        holder.usiaPemberian.setText(riwayatImunisasiArrayList.get(position).getUsia().toString());
        holder.namaPemberi.setText(riwayatImunisasiArrayList.get(position).getNamaPemeriksa());
        if (riwayatImunisasiArrayList.get(position).getKeterangan() == null) {
            holder.keteranganPemberian.setText("Tidak ada");
        }
        else {
            holder.keteranganPemberian.setText(riwayatImunisasiArrayList.get(position).getKeterangan().toString());
        }
    }


    @Override
    public int getItemCount() {
        return riwayatImunisasiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView namaImunisasi, keteranganImunisasi,
                tanggalPemberian, pemberianSelanjutnya, lokasiPemberian,
                usiaPemberian, namaPemberi, keteranganPemberian;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaImunisasi = itemView.findViewById(R.id.imunisasi_nama_text);
            keteranganImunisasi = itemView.findViewById(R.id.imunisasi_keterangan_text);
            tanggalPemberian = itemView.findViewById(R.id.imunisasi_tanggal_text);
            pemberianSelanjutnya = itemView.findViewById(R.id.imunisasi_tanggal_next_text);
            lokasiPemberian = itemView.findViewById(R.id.imunisasi_lokasi_text);
            usiaPemberian = itemView.findViewById(R.id.imunisasi_usia_text);
            namaPemberi = itemView.findViewById(R.id.nama_pemeriksa_imunisasi_text);
            keteranganPemberian = itemView.findViewById(R.id.imunisasi_keterangan_pemberian_text);

        }
    }
}
