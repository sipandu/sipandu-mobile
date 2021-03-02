package com.sipanduteam.sipandu.fragment.forgotpass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sipanduteam.sipandu.R;

public class ForgotMethod extends Fragment {

    public ForgotMethod() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_forgot_method, container, false);

        Button telegramMethod = v.findViewById(R.id.reset_telegram_button);
        telegramMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast);
                ft.replace(R.id.forgot_pass_container, new TelegramMethod());
                ft.commit();
            }
        });

        Button emailMethod = v.findViewById(R.id.reset_email_button);
        emailMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast);
                ft.replace(R.id.forgot_pass_container, new EmailMethod());
                ft.commit();
            }
        });

        return v;
    }
}