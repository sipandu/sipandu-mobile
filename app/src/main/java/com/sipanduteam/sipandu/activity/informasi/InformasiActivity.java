package com.sipanduteam.sipandu.activity.informasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.InformasiListAdapter;
import com.sipanduteam.sipandu.model.Informasi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class InformasiActivity extends AppCompatActivity {

    View barangDialogView;
    MaterialAlertDialogBuilder barangDialog;
    Button filterStartFrom, filterEndFrom;
    String filterStart, filterEnd;
    private Toolbar homeToolbar;


    private ArrayList<Informasi> informasiArrayList;

    private InformasiListAdapter informasiListAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LinearLayoutManager linearLayoutManager;


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

        informasiArrayList = new ArrayList<>();
//        for (int i = 0 ; i<10 ; i++) {
//            blogArrayList.add(new Blog("Judul misalnya " + i, "Sub judul misalnya adalah konten dimana konten lorem ipsum coba aja dulu blablabla" +i));
//        }
        recyclerView = findViewById(R.id.blog_list_view);
        informasiListAdapter = new InformasiListAdapter(this, informasiArrayList);
        gridLayoutManager = new GridLayoutManager(this, 2);
        linearLayoutManager = new LinearLayoutManager(this);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(informasiListAdapter);


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

//        ImageButton likeButtonEx = findViewById(R.id.informasi_like_button);
//        TextView likeCountEx = findViewById(R.id.informasi_like_count);
//        likeButtonEx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                likeButtonEx.setBackgroundTintList(getResources().getColorStateList(R.color.secondaryDarkColor));
//            }
//        });
    }
}