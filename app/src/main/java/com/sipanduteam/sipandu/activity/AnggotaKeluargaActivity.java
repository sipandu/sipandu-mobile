package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.KegiatanListAdapter;
import com.sipanduteam.sipandu.adapter.KeluargaAnakListAdapter;
import com.sipanduteam.sipandu.adapter.KeluargaIbuListAdapter;
import com.sipanduteam.sipandu.adapter.KeluargaLansiaListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.KeluargaDataResponse;
import com.sipanduteam.sipandu.model.posyandu.Kegiatan;
import com.sipanduteam.sipandu.model.posyandu.KegiatanPosyanduResponse;
import com.sipanduteam.sipandu.model.user.UserWithAnak;
import com.sipanduteam.sipandu.model.user.UserWithIbu;
import com.sipanduteam.sipandu.model.user.UserWithLansia;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class AnggotaKeluargaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota_keluarga);
    }
}