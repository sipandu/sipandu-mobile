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
import com.sipanduteam.sipandu.model.MasalahKesehatanLansia;
import com.sipanduteam.sipandu.model.Notifikasi;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import java.util.ArrayList;

public class NotifikasiListAdapter  extends RecyclerView.Adapter<NotifikasiListAdapter.ViewHolder>  {
    private Context mContext;
    private final ChangeDateFormat changeDateFormat = new ChangeDateFormat();
    private ArrayList<Notifikasi> notifikasiArrayList;
    private int position;

    public NotifikasiListAdapter(Context context, ArrayList<Notifikasi> notifikasiArrayList) {
        mContext = context;
        this.notifikasiArrayList = notifikasiArrayList;
    }

    @NonNull
    @Override
    public NotifikasiListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.notifikasi_card_layout, parent, false);
        return new NotifikasiListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifikasiListAdapter.ViewHolder holder, int position) {
        holder.notifTitle.setText(notifikasiArrayList.get(position).getNotifTitle());
        holder.notifDate.setText(changeDateFormat.changeDateFormat(notifikasiArrayList.get(position).getCreatedAt()));
        holder.notifContent.setText(notifikasiArrayList.get(position).getNotifContent());
    }


    @Override
    public int getItemCount() {
        return notifikasiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView notifTitle, notifDate, notifContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notifTitle = itemView.findViewById(R.id.notif_title_text);
            notifDate = itemView.findViewById(R.id.notif_time_text);
            notifContent = itemView.findViewById(R.id.notif_content_text);
        }
    }
}
