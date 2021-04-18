package com.sipanduteam.sipandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.informasi.InformasiShowActivity;
import com.sipanduteam.sipandu.model.Informasi;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InformasiListAdapter extends RecyclerView.Adapter<InformasiListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Informasi> informasiArrayList;
    private int position;
    String informasiKey = "INFORMASI_LINK", informasiTitleKey = "INFORMASI_TITLE";

    public InformasiListAdapter(Context context, ArrayList<Informasi> informasiArrayList) {
        mContext = context;
        this.informasiArrayList = informasiArrayList;
    }

    @NonNull
    @Override
    public InformasiListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.informasi_card_layout, parent, false);
        return new InformasiListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformasiListAdapter.ViewHolder holder, int position) {
        holder.circularProgressIndicator.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(informasiArrayList.get(position).getFoto())
                .into(holder.informasiImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.circularProgressIndicator.setVisibility(View.GONE);
                        holder.informasiImage.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        holder.circularProgressIndicator.setVisibility(View.GONE);
                        holder.imageFailedLoad.setVisibility(View.VISIBLE);
                    }
                });
        holder.informasiTitle.setText(informasiArrayList.get(position).getJudulInformasi());
        holder.informasiDate.setText(informasiArrayList.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return informasiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView informasiTitle;
        private final AppCompatTextView informasiDate;
        private final ImageView informasiImage;
        private final CircularProgressIndicator circularProgressIndicator;
        private final LinearLayout imageFailedLoad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            informasiTitle = itemView.findViewById(R.id.informasi_title);
            informasiDate = itemView.findViewById(R.id.informasi_date);
            informasiImage = itemView.findViewById(R.id.blog_image);
            circularProgressIndicator = itemView.findViewById(R.id.informasi_image_progress);
            imageFailedLoad = itemView.findViewById(R.id.informasi_failed_image_load);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Informasi informasi = informasiArrayList.get(position);

                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    builder.setToolbarColor(mContext.getResources().getColor(R.color.primaryColor));
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(mContext, Uri.parse("https://sipandu-test-web.herokuapp.com/blog/detail/" + informasi.getSlug()));
//
//                    Intent informasiShow = new Intent(mContext, InformasiShowActivity.class);
//                    informasiShow.putExtra(informasiKey, informasi.getSlug());
//                    informasiShow.putExtra(informasiTitleKey, informasi.getJudulInformasi());
//                    mContext.startActivity(informasiShow);
                }
            });
        }
    }
}
