package com.sipanduteam.sipandu.activity.informasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sipanduteam.sipandu.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class InformasiActivity extends AppCompatActivity {

    View barangDialogView;
    MaterialAlertDialogBuilder barangDialog;
    Button filterStartFrom, filterEndFrom;
    String filterStart, filterEnd;
    private Toolbar homeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // date picker builder
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Pilih tanggal mulai");

        final MaterialDatePicker startDatePicker = materialDateBuilder.build();
        startDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
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
                SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                Date date = new Date(selectedDate + offsetFromUTC);
                filterStart = simpleFormat.format(date);
                filterStartFrom.setText(simpleFormat.format(date));
            }
        });


        materialDateBuilder.setTitleText("Pilih tanggal akhir");
        final MaterialDatePicker endDatePicker = materialDateBuilder.build();

        endDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
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
                SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                Date date = new Date(selectedDate + offsetFromUTC);
                filterEnd = simpleFormat.format(date);
                filterEndFrom.setText(simpleFormat.format(date));
            }
        });

        barangDialogView = this.getLayoutInflater().inflate(R.layout.dialog_filter_date, null);
        filterStartFrom= barangDialogView.findViewById(R.id.start_date_button);
        filterEndFrom = barangDialogView.findViewById(R.id.end_date_button);
        filterStartFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDatePicker.show(getSupportFragmentManager(), "DATE_START_PICKER");
            }
        });

        filterEndFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        barangDialog = new MaterialAlertDialogBuilder(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar)
                .setTitle("Tentukan filter informasi")
                .setView(barangDialogView)
                .setPositiveButton("Terapkan filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        Button informasiFilterButton = findViewById(R.id.informasi_filter_button);
        informasiFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barangDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        informasiFilterButton.setText(filterStart + " - " + filterEnd);
                    }
                });
                barangDialog.show();
            }
        });
    }
}