package com.sipanduteam.sipandu.fragment.home;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.material.card.MaterialCardView;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.informasi.InformasiActivity;
import com.sipanduteam.sipandu.adapter.InformasiListBerandaAdapter;
import com.sipanduteam.sipandu.model.Informasi;
import com.sipanduteam.sipandu.viewmodel.InformasiBerandaViewModel;

import java.util.ArrayList;

import static android.view.View.GONE;
import static java.lang.Integer.valueOf;


public class BerandaFragment extends Fragment {

    LinearLayout loadingContainer, failedContainer;
    ScrollView homeContainer;
    Button refreshHome;

    private ArrayList<Informasi> informasiArrayList;

    private InformasiListBerandaAdapter informasiListBerandaAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ImageView failedImage;
    private AnimatedVectorDrawable failedAnim;
    Drawable d;

    View v;

    InformasiBerandaViewModel informasiBerandaViewModel;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        informasiArrayList = new ArrayList<>();
        informasiBerandaViewModel = ViewModelProviders.of(getActivity()).get(InformasiBerandaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_beranda, container, false);

            refreshHome = v.findViewById(R.id.home_refresh);
            refreshHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                getData();
                }
            });
            loadingContainer = v.findViewById(R.id.home_loading_container);
            homeContainer = v.findViewById(R.id.home_layout_container);
            failedContainer = v.findViewById(R.id.home_failed_container);

            failedImage = v.findViewById(R.id.home_failed_icon);
            d = failedImage.getDrawable();

            MaterialCardView telegramBotAddCard = v.findViewById(R.id.telegram_bot_add_card);
            MaterialCardView showAllInformasiButton = v.findViewById(R.id.show_all_informasi_button);
            Button dismissAddTelegramBotButton = v.findViewById(R.id.telegram_bot_add_dismiss_button);
            Button addTelegramBotButton = v.findViewById(R.id.telegram_bot_add_button);

            recyclerView = v.findViewById(R.id.home_informasi_list);
            informasiListBerandaAdapter = new InformasiListBerandaAdapter(getContext(), informasiArrayList);
            linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(informasiListBerandaAdapter);


            dismissAddTelegramBotButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    telegramBotAddCard.setVisibility(View.GONE);
                }
            });


            addTelegramBotButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://t.me/FireRex_bot";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });


            showAllInformasiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent showAllInformasi = new Intent(getActivity(), InformasiActivity.class);
                    startActivity(showAllInformasi);
                }
            });
        }
        getData();
        return v;
    }

    private void getData() {
//        informasiBerandaViewModel.init();
        setLoadingContainerVisible();
//        informasiBerandaViewModel.getInformasiRepository().observe(getViewLifecycleOwner(), informasiResponse -> {
//            List<Informasi> informasiList = informasiResponse.getInformasi();
//            informasiArrayList.addAll(informasiList);
//            informasiListBerandaAdapter.notifyDataSetChanged();
//            setHomeContainerVisible();
//        });
    }

//    public void getData() {
//        setLoadingContainerVisible();
//        BaseApi getData = RetrofitClient.buildRetrofit().create(BaseApi.class);
//        Call<InformasiResponse> informasiResponseCall = getData.getInformasiHome();
//        informasiResponseCall.enqueue(new Callback<InformasiResponse>() {
//            @Override
//            public void onResponse(Call<InformasiResponse> call, Response<InformasiResponse> response) {
//                Log.d("duar", String.valueOf(response.code()));
//                if (response.code() == 200 && response.body().getStatusCode() == 200) {
//                    informasiArrayList.addAll(response.body().getInformasi());
//                    informasiListAdapter.notifyDataSetChanged();
//                    setHomeContainerVisible();
//                }
//                else {
//                    setFailedContainerVisible();
//                    Snackbar.make(v, R.string.server_fail, Snackbar.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<InformasiResponse> call, Throwable t) {
//                setFailedContainerVisible();
//                Snackbar.make(v, R.string.server_fail, Snackbar.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        homeContainer.setVisibility(GONE);
        if (d instanceof AnimatedVectorDrawable) {
            failedAnim = (AnimatedVectorDrawable) d;
            failedAnim.start();
        }
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        homeContainer.setVisibility(GONE);
    }

    public void setHomeContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        homeContainer.setVisibility(View.VISIBLE);
    }
}