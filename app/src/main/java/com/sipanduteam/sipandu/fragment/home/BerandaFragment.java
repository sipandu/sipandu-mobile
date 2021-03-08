package com.sipanduteam.sipandu.fragment.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.sipanduteam.sipandu.R;

import static java.lang.Integer.valueOf;


public class BerandaFragment extends Fragment {

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beranda, container, false);

        MaterialCardView telegramBotAddCard = v.findViewById(R.id.telegram_bot_add_card);

        Button dismissAddTelegramBotButton = v.findViewById(R.id.telegram_bot_add_dismiss_button);
        dismissAddTelegramBotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telegramBotAddCard.setVisibility(View.GONE);
            }
        });

        Button addTelegramBotButton = v.findViewById(R.id.telegram_bot_add_button);
        addTelegramBotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://t.me/FireRex_bot";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return v;
    }
}