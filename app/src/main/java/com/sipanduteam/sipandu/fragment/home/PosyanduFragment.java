package com.sipanduteam.sipandu.fragment.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.ForgotpassActivity;
import com.sipanduteam.sipandu.activity.posyandu.KonsultasiTelegramActivity;
import com.sipanduteam.sipandu.activity.posyandu.PosyanduJadwalActivity;
import com.sipanduteam.sipandu.activity.posyandu.PosyanduMapActivity;

public class PosyanduFragment extends Fragment {

    public PosyanduFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_posyandu, container, false);
        Button openPosyanduMapButton = v.findViewById(R.id.open_posyandu_map_button);
        openPosyanduMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPosyanduMap = new Intent(getActivity(), PosyanduMapActivity.class);
                startActivity(openPosyanduMap);
            }
        });

        Button openPosyanduScheduleButton = v.findViewById(R.id.jadwal_posyandu_button);
        openPosyanduScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPosyanduSchedule = new Intent(getActivity(), PosyanduJadwalActivity.class);
                startActivity(openPosyanduSchedule);
            }
        });

        Button openKonsultasiTelegramButton = v.findViewById(R.id.konsultasi_telegram_button);
        openKonsultasiTelegramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openKonsultasiTelegram = new Intent(getActivity(), KonsultasiTelegramActivity.class);
                startActivity(openKonsultasiTelegram);
            }
        });

        Button openPosyanduLocationButton = v.findViewById(R.id.posyandu_location_button);
        openPosyanduLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://maps.google.com/?q=-8.613016979421195,115.22216762126949";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button posyanduCallButton = v.findViewById(R.id.call_posyandu_button);
        posyanduCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:089605307890"));
                startActivity(intent);
            }
        });
        return v;
    }
}