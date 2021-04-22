package com.sipanduteam.sipandu.activity.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.user.UserRegisterFirstResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout roleLayout, nikLayout;
    private TextInputEditText noKkField;
    private AutoCompleteTextView registerRoleDropdown;
    private Button backToLogin, continueRegister;
    int flagError=0;
    private ProgressDialog dialog;
    private String idKKKEY = "IDKKKEY", roleKey = "ROLEKEY", kkKey = "KKKEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backToLogin = findViewById(R.id.back_login_button);
        continueRegister = findViewById(R.id.register_continue_button);
        roleLayout = findViewById(R.id.reg_role_form);
        noKkField = findViewById(R.id.reg_nik_text_field);
        nikLayout = findViewById(R.id.reg_nik_form);

        dialog = new ProgressDialog(this);

        String[] loginRole = new String[] {"Anak", "Ibu hamil", "Lansia"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, loginRole);
        registerRoleDropdown = findViewById(R.id.reg_role_field);
        registerRoleDropdown.setAdapter(adapter);
        registerRoleDropdown.setInputType(0);
        registerRoleDropdown.setKeyListener(null);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        noKkField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(noKkField.getText().toString().length() == 0) {
                    nikLayout.setError("No. KK tidak boleh kosong");
                }
                else {
                    if (noKkField.getText().toString().length() != 16) {
                        nikLayout.setError("No. KK harus berjumlah 16 digit");
                    } else {
                        nikLayout.setError(null);
                    }
                }
            }
        });

        noKkField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if (!(registerRoleDropdown.getText().toString().matches("Anak|Ibu hamil|Lansia"))) {
                        roleLayout.setError("Silahkan pilih role");
                    }
                    else {
                        roleLayout.setError(null);
                    }
                }
            }
        });

        registerRoleDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                roleLayout.setError(null);
            }
        });




        continueRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagError = 0;
                if(noKkField.getText().toString().length() == 0) {
                    nikLayout.setError("NIK tidak boleh kosong");
                    flagError++;
                }
                //TODO sistem flag nya masih error, jad isemisal form di bawahnya udah isi, dia lolos
                else {
                    if(noKkField.getText().toString().length() != 16 ) {
                        nikLayout.setError("NIK harus berjumlah 16 digit");
                        flagError++;
                    }
                    else {
                        nikLayout.setError(null);
                    }
                }
                if (!(registerRoleDropdown.getText().toString().matches("Anak|Ibu hamil|Lansia"))) {
                    roleLayout.setError("Silahkan pilih role");
                    flagError++;
                }
                else {
                    roleLayout.setError(null);
                }

                if (flagError == 0) {
                    dialog.setMessage("Mohon tunggu...");
                    dialog.show();
                    InterfaceApi registApi = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
                    Call<UserRegisterFirstResponse> userRegisterFirstResponseCall = registApi.registerUser(noKkField.getText().toString());
                    userRegisterFirstResponseCall.enqueue(new Callback<UserRegisterFirstResponse>() {
                        @Override
                        public void onResponse(Call<UserRegisterFirstResponse> call, Response<UserRegisterFirstResponse> response) {
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                            if (response.code() == 200 && response.body().getStatusCode() == 200) {
                                Intent registerContinue = new Intent(getApplicationContext(), RegisterContinueActivity.class);
                                if (response.body().getIdKK() != null) {
                                    registerContinue.putExtra(idKKKEY, response.body().getIdKK());
                                }
                                else {
                                    registerContinue.putExtra(kkKey, noKkField.getText().toString());
                                }
                                registerContinue.putExtra(roleKey, registerRoleDropdown.getText().toString());
                                startActivity(registerContinue);
                                finish();
                            }
                            else {
                                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Something went duar meledak yey api nya meledak", Snackbar.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserRegisterFirstResponse> call, Throwable t) {
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                            Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Something went duar meledak yey api nya meledak", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}