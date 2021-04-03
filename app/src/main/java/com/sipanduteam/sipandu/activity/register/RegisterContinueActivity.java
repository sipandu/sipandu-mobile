package com.sipanduteam.sipandu.activity.register;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.adapter.DesaRegisterSelectionAdapter;
import com.sipanduteam.sipandu.activity.adapter.KabupatenRegisterSelectionAdapter;
import com.sipanduteam.sipandu.activity.adapter.KecamatanRegisterSelectionAdapter;
import com.sipanduteam.sipandu.activity.adapter.PosyanduRegisterSelectionAdapter;
import com.sipanduteam.sipandu.api.BaseApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.Desa;
import com.sipanduteam.sipandu.model.Kabupaten;
import com.sipanduteam.sipandu.model.Kecamatan;
import com.sipanduteam.sipandu.model.Posyandu;
import com.sipanduteam.sipandu.model.register.RegistDataPosyanduResponse;
import com.sipanduteam.sipandu.model.register.RegisterResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterContinueActivity extends AppCompatActivity {


    private TextInputLayout kartuKeluargaLayout, kabupatenLayout, kecamatanLayout, desaLayout, posyanduLayout,
            namaLayout, emailLayout, passwordLayout, passwordConfirmLayout;
    private TextInputEditText kartuKeluargaForm, namaField, emailField, passwordField, passwordConfirmField;
    private AutoCompleteTextView kabupatenField, kecamatanField, desaField, posyanduField;
    private Button backToLogin;
    private String idKKKEY = "IDKKKEY", roleKey = "ROLEKEY";
    private int idKK;
    private ProgressDialog dialog;
    private Button registerSubmitButton;
    BaseApi retro;
    List<Kabupaten> kabupatens = new ArrayList<>();
    List<Kecamatan> kecamatans = new ArrayList<>();
    List<Desa> desas = new ArrayList<>();
    List<Posyandu> posyandus = new ArrayList<>();
    private Kabupaten kabupaten;
    private Kecamatan kecamatan;
    private Desa desa;
    private Posyandu posyandu;
    Bundle extras;
    AlertDialog registerCompleteDialog;
    View registerCompleteView;
    AnimatedVectorDrawable registerCompleteAnimation;
    Drawable registerComplteDrawable;
    ImageView registerCompleteIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_continue);

        backToLogin = findViewById(R.id.back_login_button);
        backToLogin = findViewById(R.id.back_login_button);
        kartuKeluargaLayout = findViewById(R.id.kartu_keluarga_form);
        kartuKeluargaForm = findViewById(R.id.kartu_keluarga_field);
        kabupatenLayout = findViewById(R.id.reg_kabupaten_form);
        kabupatenField = findViewById(R.id.reg_kabupaten_field);
        kecamatanField = findViewById(R.id.reg_kecamatan_field);
        kecamatanLayout = findViewById(R.id.reg_kecamatan_form);
        desaField = findViewById(R.id.reg_desa_field);
        desaLayout = findViewById(R.id.reg_desa_form);
        posyanduField = findViewById(R.id.reg_banjar_field);
        posyanduLayout = findViewById(R.id.reg_banjar_form);
        registerSubmitButton = findViewById(R.id.register_submit_button);
        namaField = findViewById(R.id.reg_nama_field);
        emailField = findViewById(R.id.reg_email_field);
        passwordField = findViewById(R.id.reg_password_field);
        passwordConfirmField = findViewById(R.id.reg_password_confirm_field);

        extras = getIntent().getExtras();

        if (extras.containsKey(idKKKEY)) {
            kartuKeluargaLayout.setEnabled(false);
            kartuKeluargaForm.setText("Kartu keluarga sudah terdaftar pada sistem");
        }

        retro = RetrofitClient.buildRetrofit().create(BaseApi.class);
        dialog = new ProgressDialog(this);

        getAllPosyandu();

//        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, kabupatens);
        KabupatenRegisterSelectionAdapter kabupatenRegisterSelectionAdapter = new KabupatenRegisterSelectionAdapter(this, kabupatens);
        KecamatanRegisterSelectionAdapter kecamatanRegisterSelectionAdapter = new KecamatanRegisterSelectionAdapter(this, kecamatans);
        DesaRegisterSelectionAdapter desaRegisterSelectionAdapter = new DesaRegisterSelectionAdapter(this, desas);
        PosyanduRegisterSelectionAdapter posyanduRegisterSelectionAdapter = new PosyanduRegisterSelectionAdapter(this, posyandus);
        kabupatenField.setAdapter(kabupatenRegisterSelectionAdapter);
        kecamatanField.setAdapter(kecamatanRegisterSelectionAdapter);
        desaField.setAdapter(desaRegisterSelectionAdapter);
        posyanduField.setAdapter(posyanduRegisterSelectionAdapter);
        kabupatenField.setThreshold(100);
        kabupatenField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kabupatenField.showDropDown();
                kecamatanLayout.setEnabled(false);
                kecamatanLayout.setHint("Pilih kabupaten terlebih dahulu");

                desaLayout.setHint("Pilih kecamatan terlebih dahulu");
                desaLayout.setEnabled(false);

                posyanduLayout.setHint("Pilih desa/kelurahan");
                posyanduLayout.setEnabled(false);

                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

            }
        });

        kabupatenField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                kabupaten = (Kabupaten) adapterView.getItemAtPosition(i);
                kabupatenField.setText(kabupaten.getNamaKabupaten());
                kecamatans.clear();
                kecamatans.addAll(kabupaten.getKecamatan());
                kecamatanRegisterSelectionAdapter.notifyDataSetChanged();
                kecamatanLayout.setHint("Pilih kecamatan");
                kecamatanLayout.setEnabled(true);
            }
        });

        kecamatanField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                kecamatan = (Kecamatan) adapterView.getItemAtPosition(i);
                kecamatanField.setText(kecamatan.getNamaKecamatan());
                desas.clear();
                desas.addAll(kecamatan.getDesa());
                desaRegisterSelectionAdapter.notifyDataSetChanged();
                desaLayout.setHint("Pilih desa");
                desaLayout.setEnabled(true);
            }
        });

        desaField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                desa = (Desa) adapterView.getItemAtPosition(i);
                desaField.setText(desa.getNamaDesa());
                posyandus.addAll(desa.getPosyandu());
                posyanduRegisterSelectionAdapter.notifyDataSetChanged();
                posyanduLayout.setHint("Pilih banjar");
                posyanduLayout.setEnabled(true);
            }
        });

        posyanduField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posyandu = (Posyandu) adapterView.getItemAtPosition(i);
                posyanduField.setText(posyandu.getBanjar());
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        kartuKeluargaForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFile();
            }
        });

        kartuKeluargaForm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    pickFile();
                }
            }
        });

        registerSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        registerCompleteView = LayoutInflater.from(this).inflate(R.layout.dialog_register_success, null, false);

        registerCompleteDialog = new MaterialAlertDialogBuilder(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar)
                .setView(registerCompleteView)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();
    }

    public void pickFile() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("image/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            Uri uri = data.getData();
            String src = uri.getPath();
            Toast.makeText(getApplicationContext(), "test pick file, data loc:" + src, Toast.LENGTH_SHORT).show();
        }
    }

    public void getAllPosyandu() {
        dialog.setMessage("Mohon tunggu...");
        dialog.show();
        Call<RegistDataPosyanduResponse> registDataPosyanduResponseCall = retro.registerDataPosyandu();
        registDataPosyanduResponseCall.enqueue(new Callback<RegistDataPosyanduResponse>() {
            @Override
            public void onResponse(Call<RegistDataPosyanduResponse> call, Response<RegistDataPosyanduResponse> response) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                kabupatens.addAll(response.body().getData());
//                for (Kabupaten kabupaten1 : listKabupaten) {
//                    String namaKabupaten = kabupaten1.getNamaKabupaten();
//                    kabupatens.add(kabupaten1);
//                }
            }
            @Override
            public void onFailure(Call<RegistDataPosyanduResponse> call, Throwable t) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Something went duar meledak yey api nya meledak", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void register() {
        dialog.setMessage("Mohon tunggu...");
        dialog.show();
        Call<RegisterResponse> registerResponseCall = null;
        if (extras.getString(roleKey).equals("Anak")) {
            registerResponseCall = retro.registerAnak(extras.getInt(idKKKEY), emailField.getText().toString(),
                    passwordField.getText().toString(), posyandu.getId(), namaField.getText().toString());
        }
        else if (extras.getString(roleKey).equals("Ibu hamil")) {
            registerResponseCall = retro.registerBumil(extras.getInt(idKKKEY), emailField.getText().toString(),
                    passwordField.getText().toString(), posyandu.getId(), namaField.getText().toString());
        }
        else if (extras.getString(roleKey).equals("Lansia")) {
            registerResponseCall = retro.registerLansia(extras.getInt(idKKKEY), emailField.getText().toString(),
                    passwordField.getText().toString(), posyandu.getId(), namaField.getText().toString());
        }
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                Log.d("tolong", String.valueOf(response.code()));
                if(response.body().getStatusRegister().equals("email sama")) {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Email sudah di gunakan, silahkan gunakan email yang lain", Snackbar.LENGTH_SHORT).show();
                }
                else {
//                    registerCompleteDialog.show();
                    Intent registerComplete = new Intent(getApplicationContext(), RegisterComplete.class);
                    startActivity(registerComplete);
                    finish();
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Something went duar meledak yey api nya meledak", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}