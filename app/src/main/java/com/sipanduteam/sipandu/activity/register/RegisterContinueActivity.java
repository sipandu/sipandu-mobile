package com.sipanduteam.sipandu.activity.register;

import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;

import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.DesaRegisterSelectionAdapter;
import com.sipanduteam.sipandu.adapter.KabupatenRegisterSelectionAdapter;
import com.sipanduteam.sipandu.adapter.KecamatanRegisterSelectionAdapter;
import com.sipanduteam.sipandu.adapter.PosyanduRegisterSelectionAdapter;
import com.sipanduteam.sipandu.api.BaseApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.Desa;
import com.sipanduteam.sipandu.model.Kabupaten;
import com.sipanduteam.sipandu.model.Kecamatan;
import com.sipanduteam.sipandu.model.Posyandu;
import com.sipanduteam.sipandu.model.register.RegistDataPosyanduResponse;
import com.sipanduteam.sipandu.model.register.RegisterResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterContinueActivity extends AppCompatActivity {


    private TextInputLayout kartuKeluargaLayout, kabupatenLayout, kecamatanLayout, desaLayout, posyanduLayout,
            namaLayout, emailLayout, passwordLayout, passwordConfirmLayout;
    private TextInputEditText kartuKeluargaForm, namaField, emailField, passwordField, passwordConfirmField;
    private AutoCompleteTextView kabupatenField, kecamatanField, desaField, posyanduField;
    private Button backToLogin;
    private String idKKKEY = "IDKKKEY", roleKey = "ROLEKEY", kkKey = "KKKEY";
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

//    AlertDialog registerCompleteDialog;
//    View registerCompleteView;

    private boolean fileSelected = false, kabupatenSelected = false,
            kecamatanSelected = false, desaSelected = false, posyanduSelected = false, namaCorrect = false,
            emailCorrect = false, passwordCorrect = false, isPasswordCorrect = false;

    Uri uriFile;
    String srcFile;
    String tempFolder;


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
        namaLayout = findViewById(R.id.reg_nama_form);
        emailLayout = findViewById(R.id.reg_email_form);
        passwordLayout = findViewById(R.id.reg_password_form);
        passwordConfirmLayout = findViewById(R.id.reg_password_confirm_form);
        namaField = findViewById(R.id.reg_nama_field);
        emailField = findViewById(R.id.reg_email_field);
        passwordField = findViewById(R.id.reg_password_field);
        passwordConfirmField = findViewById(R.id.reg_password_confirm_field);

        extras = getIntent().getExtras();
        String role = extras.getString(roleKey).toLowerCase();

        namaField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(namaField.getText().toString().length() == 0) {
                    namaLayout.setError("Nama " + role + " tidak boleh kosong");
                    namaCorrect = false;
                }
                else {
                    if (namaField.getText().toString().length() > 50) {
                        namaLayout.setError("Nama " + role + " tidak boleh lebih dari 50 karakter");
                        namaCorrect = false;
                    }
                    else if (namaField.getText().toString().length() < 2) {
                        namaLayout.setError("Nama " + role + " tidak boleh kurang dari 2 karakter");
                        namaCorrect = false;
                    }
                    else {
                        if ( isNameValid(namaField.getText().toString()) ) {
                            namaLayout.setError(null);
                            namaCorrect = true;
                        }
                        else {
                            namaLayout.setError("Periksa kembali nama yang di inputkan");
                            namaCorrect = false;
                        }
                    }
                }
            }
        });

        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!isEmailValid(emailField.getText().toString())) {
                    emailLayout.setError("Email tidak valid");
                    emailCorrect = false;
                }
                else {
                    emailLayout.setError(null);
                    emailCorrect = true;
                }
            }
        });

        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(passwordField.getText().toString().length() > 50) {
                    passwordLayout.setError("Password tidak boleh lebih dari 50 karakter");
                    passwordCorrect = false;
                }
                else if(passwordField.getText().toString().length() < 8) {
                    passwordLayout.setError("Password tidak boleh kurang dari 8 karakter");
                    passwordCorrect = false;
                }
                else {
                    passwordLayout.setError(null);
                    passwordCorrect = true;
                }
            }
        });

        passwordConfirmField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!(passwordField.getText().toString().equals(passwordConfirmField.getText().toString()))) {
                    passwordConfirmLayout.setError("Password tidak cocok");
                    isPasswordCorrect = false;
                }
                else {
                    passwordConfirmLayout.setError(null);
                    isPasswordCorrect = true;
                }
            }
        });


        tempFolder = getApplicationContext().getCacheDir().getAbsolutePath();

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
        kecamatanField.setThreshold(100);
        desaField.setThreshold(100);
        posyanduField.setThreshold(100);
//        kabupatenField.setKeyListener(null);
//        kecamatanField.setKeyListener(null);
//        desaField.setKeyListener(null);
//        posyanduField.setKeyListener(null);

//        kabupatenField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                kabupatenField.showDropDown();
////                kecamatanField.setText(null);
////
////                desaLayout.setHint("Pilih kecamatan terlebih dahulu");
////                desaLayout.setEnabled(false);
////                desaField.setText(null);
////
////                posyanduLayout.setHint("Pilih desa/kelurahan");
//                posyanduLayout.setEnabled(false);
////                posyanduField.setText(null);
//            }
//        });

//        kabupatenLayout.setEndIconOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                kabupatenField.showDropDown();
//                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (in.isActive()) {
//                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
//                }
//            }
//        });


        kabupatenField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                kabupaten = (Kabupaten) adapterView.getItemAtPosition(i);
                kabupatenField.setText(kabupaten.getNamaKabupaten());
                if (!kecamatans.isEmpty()) {
                    kecamatans.clear();
                }

                kabupatenSelected = true;
                kecamatanSelected = false;
                desaSelected = false;
                posyanduSelected = false;

                kecamatans.addAll(kabupaten.getKecamatan());
                kecamatanRegisterSelectionAdapter.notifyDataSetChanged();
                kecamatanLayout.setHint("Pilih kecamatan");
                kecamatanLayout.setEnabled(true);
                kecamatanField.setText("");

                desaLayout.setHint("Pilih kecamatan terlebih dahulu");
                desaLayout.setEnabled(false);
                desaField.setText("");

                posyanduLayout.setHint("Pilih desa/kelurahan terlebih dahulu");
                posyanduLayout.setEnabled(false);
                posyanduField.setText("");
            }
        });

//        kecamatanField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                kecamatanField.showDropDown();
//            }
//        });
//
//        kecamatanLayout.setEndIconOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                kecamatanField.showDropDown();
////                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////                if (in.isActive()) {
////                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
////                }
//            }
//        });

        kecamatanField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                kecamatan = (Kecamatan) adapterView.getItemAtPosition(i);
                kecamatanField.setText(kecamatan.getNamaKecamatan());
                if (!desas.isEmpty()) {
                    desas.clear();
                }

                kecamatanSelected = true;
                desaSelected = false;
                posyanduSelected = false;

                desas.addAll(kecamatan.getDesa());
                desaRegisterSelectionAdapter.notifyDataSetChanged();
                desaLayout.setHint("Pilih desa");
                desaLayout.setEnabled(true);
                desaField.setText("");
                posyanduField.setText("");
                posyanduLayout.setHint("Pilih desa/kelurahan terlebih dahulu");
                posyanduLayout.setEnabled(false);
            }
        });

//        desaField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                desaField.showDropDown();
//            }
//        });
//
//        desaLayout.setEndIconOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                desaField.showDropDown();
////                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////                if (in.isActive()) {
////                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
////                }
//            }
//        });



        desaField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                desa = (Desa) adapterView.getItemAtPosition(i);
                desaField.setText(desa.getNamaDesa());
                if (!posyandus.isEmpty()) {
                    posyandus.clear();
                }

                desaSelected = true;
                posyanduSelected = false;

                posyanduField.setText("");
                posyandus.addAll(desa.getPosyandu());
                posyanduRegisterSelectionAdapter.notifyDataSetChanged();
                if (posyandus.size() == 0) {
                    posyanduLayout.setHint("Tidak ada posyandu");
                    posyanduLayout.setEnabled(false);
                }
                else {
                    posyanduLayout.setHint("Pilih banjar");
                    posyanduLayout.setEnabled(true);
                }
            }
        });

        posyanduField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posyandu = (Posyandu) adapterView.getItemAtPosition(i);
                posyanduField.setText(posyandu.getBanjar());
                posyanduSelected = true;
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
                if( !(fileSelected && kabupatenSelected
                        && kecamatanSelected && desaSelected
                        && posyanduSelected && namaCorrect && emailCorrect
                        && passwordCorrect && isPasswordCorrect)) {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Periksa kembali data yang di inputkan", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    register();
                }
            }
        });

//        registerCompleteView = LayoutInflater.from(this).inflate(R.layout.dialog_register_success, null, false);
//
//        registerCompleteDialog = new MaterialAlertDialogBuilder(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar)
//                .setView(registerCompleteView)
//                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                }).create();
    }

    public void pickFile() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        String [] mimeTypes = {"image/png", "image/jpg","image/jpeg"};
        chooseFile.setType("*/*");
        chooseFile.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, 1);

//        Intent chooseFile = new Intent(Intent.ACTION_PICK);
//        chooseFile.setType("image/*");
//        chooseFile = Intent.createChooser(chooseFile, "Pilih file kartu keluarga");
//        startActivityForResult(chooseFile, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            uriFile = data.getData();
            srcFile = uriFile.getPath();
            kartuKeluargaForm.setText("");
            fileSelected = false;
            if (DocumentFile.fromSingleUri(this, uriFile).length() > 5000000) {
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Berkas terlalu besar", Snackbar.LENGTH_SHORT).show();
                kartuKeluargaLayout.setError("Berkas terlalu besar");
            }
            else {
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Berkas kartu keluarga berhasil di pilih", Snackbar.LENGTH_SHORT).show();
                kartuKeluargaForm.setText(DocumentFile.fromSingleUri(this, uriFile).getName());
                fileSelected = true;
                kartuKeluargaLayout.setError(null);
            }
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
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void register() {
        dialog.setMessage("Mohon tunggu...");
        dialog.show();

        RequestBody emailData, passwordData, posyanduData, namaData, kkData, noKK;
        Call<RegisterResponse> registerResponseCall = null;

        emailData = RequestBody.create(MediaType.parse("text/plain"), emailField.getText().toString());
        passwordData = RequestBody.create(MediaType.parse("text/plain"), passwordField.getText().toString());
        posyanduData = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(posyandu.getId()));
        namaData = RequestBody.create(MediaType.parse("text/plain"), namaField.getText().toString());

        if (extras.containsKey(kkKey) && extras.getString(kkKey).length() != 0) {
            noKK = RequestBody.create(MediaType.parse("text/plain"), extras.getString(kkKey));
            MultipartBody.Part kkFile;
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            File filesDir = getFilesDir();
            File imageFile = new File(filesDir, extras.getString(kkKey) + ".jpg");
            OutputStream os;
            try {
                os = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
            }
            kkData = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
            kkFile = MultipartBody.Part.createFormData("file", imageFile.getName(), kkData);

            if (extras.getString(roleKey).equals("Anak")) {
                registerResponseCall = retro.registerAnakWithKK(
                        emailData,
                        passwordData,
                        posyanduData,
                        namaData,
                        noKK,
                        kkFile);
            }
            else if (extras.getString(roleKey).equals("Ibu hamil")) {
                registerResponseCall = retro.registerBumilWithKK(
                        emailData,
                        passwordData,
                        posyanduData,
                        namaData,
                        noKK,
                        kkFile);
            }
            else if (extras.getString(roleKey).equals("Lansia")) {
                registerResponseCall = retro.registerLansiaWithKK(
                        emailData,
                        passwordData,
                        posyanduData,
                        namaData,
                        noKK,
                        kkFile);
            }
        }
        else {
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
        }
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
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
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isNameValid(String text){
        return text.matches("^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$");
    }
}