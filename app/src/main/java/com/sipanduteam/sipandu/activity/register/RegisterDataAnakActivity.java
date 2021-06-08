package com.sipanduteam.sipandu.activity.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.GenericApiResponse;
import com.sipanduteam.sipandu.model.Kabupaten;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDataAnakActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private TextInputLayout namaAyahLayout, namaIbuLayout, notelpLayout, telegramLayout, nikLayout,
            tempatLahirLayout, tglLahirLayout, jkLayout, alamatLayout, anakKeLayout, pendidikanAyahLayout,
    pekerjaanAyahLayout, pendidikanIbuLayout, pekerjaanIbuLayout, tanggunganLayout, jknLayout, rujukanLayout,
    agamaLayout, goldarLayout, masaBerlakuJknLayout;
    private TextInputEditText namaAyahField, namaIbuField, notelpField, telegramField, nikField,
            tempatLahirField, tglLahirField, alamatField, anakKeField, rujukanField, jknField, masaBerlakuJknField;
    private AutoCompleteTextView jkAutoComplete, pendidikanAyahAutoComplete, pendidikanIbuAutoComplete,
            pekerjaanAyahAutoComplete, pekerjaanIbuAutoComplete, agamaAutoComplete,
            goldarAutoComplete, tanggunganAutoComplete;
    private Button backToLogin, submitAnak;
    private ProgressDialog dialog;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data_anak);

        extras = getIntent().getExtras();
        dialog = new ProgressDialog(this);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String greeting;

        if (hour>= 12 && hour < 17) {
            greeting = "Selamat siang";
        } else if (hour >= 17 && hour < 21) {
            greeting = "Selamat sore";
        } else if (hour >= 21 && hour < 24) {
            greeting = "Selamat malam";
        } else {
            greeting = "Selamat pagi";
        }
        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setTitle(greeting);

        namaAyahLayout = findViewById(R.id.reg_anak_nama_ayah_form);
        namaIbuLayout = findViewById(R.id.reg_anak_nama_ibu_form);
        notelpLayout = findViewById(R.id.reg_anak_notelp_form);
        telegramLayout = findViewById(R.id.reg_anak_telegram_form);
        nikLayout = findViewById(R.id.reg_anak_nik_form);
        tempatLahirLayout = findViewById(R.id.reg_anak_tempat_lahir_form);
        tglLahirLayout = findViewById(R.id.reg_anak_tanggal_lahir_form);
        jkLayout = findViewById(R.id.reg_anak_jk_form);
        alamatLayout = findViewById(R.id.reg_anak_alamat_form);
        anakKeLayout = findViewById(R.id.reg_anak_anakke_form);
        rujukanLayout = findViewById(R.id.reg_anak_faskes_rujukan_form);
        jknLayout = findViewById(R.id.reg_anak_jkn_form);
        pendidikanAyahLayout = findViewById(R.id.reg_anak_pendidkan_ayah_form);
        pendidikanIbuLayout = findViewById(R.id.reg_anak_pendidkan_ibu_form);
        pekerjaanAyahLayout = findViewById(R.id.reg_anak_pekerjaan_ayah_form);
        pendidikanIbuLayout = findViewById(R.id.reg_anak_pekerjaan_ibu_form);
        agamaLayout = findViewById(R.id.reg_anak_agama_form);
        goldarLayout = findViewById(R.id.reg_anak_goldar_form);
        tanggunganLayout = findViewById(R.id.reg_anak_tanggungan_form);
        masaBerlakuJknLayout = findViewById(R.id.reg_anak_masa_berlaku_jkn_form);

        rujukanLayout.setEnabled(false);
        jknLayout.setEnabled(false);
        masaBerlakuJknLayout.setEnabled(false);

        backToLogin = findViewById(R.id.reg_anak_backtologin);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submitAnak = findViewById(R.id.reg_anak_submit_button);
        submitAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });

        namaAyahField = findViewById(R.id.reg_anak_nama_ayah_field);
        namaIbuField = findViewById(R.id.reg_anak_nama_ibu_field);
        notelpField = findViewById(R.id.reg_anak_notelp_field);
        telegramField = findViewById(R.id.reg_anak_telegram_field);
        nikField = findViewById(R.id.reg_anak_nik_field);
        tempatLahirField = findViewById(R.id.reg_anak_tempat_lahir_field);
        tglLahirField = findViewById(R.id.reg_anak_tanggal_lahir_field);
        alamatField = findViewById(R.id.reg_anak_alamat_field);
        anakKeField = findViewById(R.id.reg_anak_anakke_field);
        rujukanField = findViewById(R.id.reg_anak_faskes_rujukan_field);
        jknField = findViewById(R.id.reg_anak_jkn_field);
        pendidikanAyahAutoComplete = findViewById(R.id.reg_anak_pendidkan_ayah_field);
        pendidikanIbuAutoComplete = findViewById(R.id.reg_anak_pendidkan_ibu_field);
        pekerjaanAyahAutoComplete = findViewById(R.id.reg_anak_pekerjaan_ayah_field);
        pekerjaanIbuAutoComplete = findViewById(R.id.reg_anak_pekerjaan_ibu_field);
        agamaAutoComplete = findViewById(R.id.reg_anak_agama_field);
        goldarAutoComplete = findViewById(R.id.reg_anak_goldar_field);
        tanggunganAutoComplete = findViewById(R.id.reg_anak_tanggungan_field);
        masaBerlakuJknField = findViewById(R.id.reg_masa_berlaku_jkn_field);

        String[] pekerjaan = new String[] {"Guru", "Lain-lain", "Pegawai Swasta", "Pemilik Usaha", "PNS", "Polri", "TNI"};
        String[] pendidikan = new String[] {"Diploma", "S1", "S2", "S3", "SD", "SLTA", "SMA", "SMK", "SMP"};
        ArrayAdapter adapter1 = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, pendidikan);
        pendidikanAyahAutoComplete.setAdapter(adapter1);
        pendidikanAyahAutoComplete.setInputType(0);
        pendidikanAyahAutoComplete.setKeyListener(null);

        ArrayAdapter adapter2 = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, pendidikan);
        pendidikanIbuAutoComplete.setAdapter(adapter2);
        pendidikanIbuAutoComplete.setInputType(0);
        pendidikanIbuAutoComplete.setKeyListener(null);

        ArrayAdapter adapter3 = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, pekerjaan);
        pekerjaanAyahAutoComplete.setAdapter(adapter3);
        pekerjaanAyahAutoComplete.setInputType(0);
        pekerjaanAyahAutoComplete.setKeyListener(null);

        ArrayAdapter adapter4 = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, pekerjaan);
        pekerjaanIbuAutoComplete.setAdapter(adapter4);
        pekerjaanIbuAutoComplete.setInputType(0);
        pekerjaanIbuAutoComplete.setKeyListener(null);

        String[] agama = new String[] {"Budha", "Hindu", "Islam", "Katolik", "Konghucu", "Protestan"};
        ArrayAdapter adapter5 = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, agama);
        agamaAutoComplete.setAdapter(adapter5);
        agamaAutoComplete.setInputType(0);
        agamaAutoComplete.setKeyListener(null);

        String[] goldar = new String[] {"A", "B", "AB", "O"};
        ArrayAdapter adapter6 = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, goldar);
        goldarAutoComplete.setAdapter(adapter6);
        goldarAutoComplete.setInputType(0);
        goldarAutoComplete.setKeyListener(null);

        String[] tanggungan = new String[] {"Tanpa Tanggungan", "Dengan Tanggungan"};
        ArrayAdapter adapter7 = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, tanggungan);
        tanggunganAutoComplete.setAdapter(adapter7);
        tanggunganAutoComplete.setInputType(0);
        tanggunganAutoComplete.setKeyListener(null);

        tanggunganAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Dengan Tanggungan")) {
                    rujukanLayout.setEnabled(true);
                    jknLayout.setEnabled(true);
                    masaBerlakuJknLayout.setEnabled(true);
                }
                else {
                    rujukanLayout.setEnabled(false);
                    jknLayout.setEnabled(false);
                    masaBerlakuJknLayout.setEnabled(false);
                }
            }
        });

        String[] loginRole = new String[] {"laki-laki", "perempuan"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, loginRole);
        jkAutoComplete = findViewById(R.id.reg_anak_jk_field);
        jkAutoComplete.setAdapter(adapter);
        jkAutoComplete.setInputType(0);
        jkAutoComplete.setKeyListener(null);

        MaterialDatePicker.Builder materialDateBuilder2 = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder2.setTitleText("Pilih masa berlaku JKN anak");
        final MaterialDatePicker materialDatePicker2 = materialDateBuilder2.build();

        materialDatePicker2.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selectedDate) {
                // link: https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
                // user has selected a date
                // format the date and set the text of the input box to be the selected date
                // right now this format is hard-coded, this will change
                ;
                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                Date date = new Date(selectedDate + offsetFromUTC);

                masaBerlakuJknField.setText(simpleFormat.format(date));
            }
        });

        masaBerlakuJknField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker2.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        masaBerlakuJknField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    materialDatePicker2.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                }
            }
        });

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Pilih tanggal lahir anak");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        tglLahirField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        tglLahirField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                }
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selectedDate) {
                // link: https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
                // user has selected a date
                // format the date and set the text of the input box to be the selected date
                // right now this format is hard-coded, this will change
                ;
                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                Date date = new Date(selectedDate + offsetFromUTC);

                tglLahirField.setText(simpleFormat.format(date));
            }
        });

        Log.d("duar uji", tglLahirField.getText().toString());
    }

    public void submitData() {
        dialog.setMessage("Mohon tunggu...");
        dialog.show();
        InterfaceApi submitData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<GenericApiResponse> genericApiResponseCall = submitData.registerDataAnak(telegramField.getText().toString(),
                nikField.getText().toString(), namaAyahField.getText().toString(), namaIbuField.getText().toString(),
                tempatLahirField.getText().toString(),tglLahirField.getText().toString(), jkAutoComplete.getText().toString(),
                notelpField.getText().toString(), Integer.parseInt(anakKeField.getText().toString()),
                alamatField.getText().toString(), extras.getString("EMAILUSER"),
                jknField.getText().toString(), masaBerlakuJknField.getText().toString(), pekerjaanAyahAutoComplete.getText().toString(),
                pekerjaanIbuAutoComplete.getText().toString(), pendidikanAyahAutoComplete.getText().toString(), pendidikanIbuAutoComplete.getText().toString(),
                tanggunganAutoComplete.getText().toString(), rujukanField.getText().toString(), agamaAutoComplete.getText().toString(),
                goldarAutoComplete.getText().toString());
        genericApiResponseCall.enqueue(new Callback<GenericApiResponse>() {
            @Override
            public void onResponse(Call<GenericApiResponse> call, Response<GenericApiResponse> response) {
                Log.d("yha", response.toString());
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Log.d("apaje", tglLahirField.getText().toString());
                if (response.code() == 200 && response.body().getStatusCode() == 200 && response.body().getStatus().equals("berhasil")) {
                    Intent registerDataComplete = new Intent(getApplicationContext(), RegisterDataComplete.class);
                    startActivity(registerDataComplete);
                    finish();
                }
                else {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericApiResponse> call, Throwable t) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}