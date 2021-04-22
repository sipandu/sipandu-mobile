package com.sipanduteam.sipandu.activity.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDataLansiaActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private TextInputLayout notelpLayout, telegramLayout, nikLayout,
            tempatLahirLayout, tglLahirLayout, alamatLayout, jkLayout, statusLansiaLayout;
    private TextInputEditText notelpField, telegramField, nikField,
            tempatLahirField, tglLahirField, alamatField;
    private AutoCompleteTextView jkAutoComplete, statusLansiaAutoComplete;
    private Button backToLogin, submitLansia;
    private ProgressDialog dialog;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data_lansia);

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

        notelpLayout = findViewById(R.id.reg_ibu_notelp_form);
        telegramLayout = findViewById(R.id.reg_ibu_telegram_form);
        nikLayout = findViewById(R.id.reg_ibu_nik_form);
        tempatLahirLayout = findViewById(R.id.reg_ibu_tempat_lahir_form);
        tglLahirLayout = findViewById(R.id.reg_ibu_tanggal_lahir_form);
        alamatLayout = findViewById(R.id.reg_ibu_alamat_form);

        String[] lansiaStatus = new String[] {"Lansia", "Lansia Beresiko", "Pra Lansia"};
        ArrayAdapter lansiaStatusAdapter = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, lansiaStatus);
        statusLansiaAutoComplete = findViewById(R.id.reg_lansia_status_field);
        statusLansiaAutoComplete.setAdapter(lansiaStatusAdapter);
        statusLansiaAutoComplete.setInputType(0);
        statusLansiaAutoComplete.setKeyListener(null);

        String[] loginRole = new String[] {"laki-laki", "perempuan"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, loginRole);
        jkAutoComplete = findViewById(R.id.reg_lansia_jk_field);
        jkAutoComplete.setAdapter(adapter);
        jkAutoComplete.setInputType(0);
        jkAutoComplete.setKeyListener(null);


        backToLogin = findViewById(R.id.reg_lansia_backtologin);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submitLansia = findViewById(R.id.reg_lansia_submit_button);
        submitLansia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });

        notelpField = findViewById(R.id.reg_lansia_notelp_field);
        telegramField = findViewById(R.id.reg_lansia_telegram_field);
        nikField = findViewById(R.id.reg_lansia_nik_field);
        tempatLahirField = findViewById(R.id.reg_lansia_tempat_lahir_field);
        tglLahirField = findViewById(R.id.reg_lansia_tanggal_lahir_field);
        alamatField = findViewById(R.id.reg_lansia_alamat_field);

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
        Call<GenericApiResponse> genericApiResponseCall = submitData.registerDataLansia(
                telegramField.getText().toString(),
                nikField.getText().toString(),
                tempatLahirField.getText().toString(),
                tglLahirField.getText().toString(),
                notelpField.getText().toString(),
                jkAutoComplete.getText().toString(),
                alamatField.getText().toString(),
                statusLansiaAutoComplete.getText().toString(),
                extras.getString("EMAILUSER"));
        genericApiResponseCall.enqueue(new Callback<GenericApiResponse>() {
            @Override
            public void onResponse(Call<GenericApiResponse> call, Response<GenericApiResponse> response) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Log.d("apaje", String.valueOf(response.code()));
                if (response.code() == 200 && response.body().getStatusCode() == 200 && response.body().getStatus().equals("berhasil")) {
                    Intent registerDataComplete = new Intent(getApplicationContext(), RegisterDataComplete.class);
                    startActivity(registerDataComplete);
                    finish();
                } else {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericApiResponse> call, Throwable t) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}