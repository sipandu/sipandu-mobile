package com.sipanduteam.sipandu.activity.informasi;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.InformasiKaroselListAdapter;
import com.sipanduteam.sipandu.adapter.InformasiListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.fragment.widget.FilterInformasiFragment;
import com.sipanduteam.sipandu.model.Informasi;
import com.sipanduteam.sipandu.model.InformasiResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class InformasiActivity extends AppCompatActivity implements FilterInformasiFragment.ItemClickListener {

    View barangDialogView;
    MaterialAlertDialogBuilder barangDialog;
    Button filterStartFrom, filterEndFrom;
    String filterStart, filterEnd;
    private Toolbar homeToolbar;
    private ArrayList<Informasi> informasiArrayList, informasiKaroselArrayList;
    private InformasiListAdapter informasiListAdapter;
    InformasiKaroselListAdapter informasiKaroselListAdapter;
    private RecyclerView recyclerView, recyclerViewKarosel;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LinearLayoutManager linearLayoutManager, linearLayoutManagerKarosel;
    private ExtendedFloatingActionButton filterFab;
    private NestedScrollView informasiScrollView;
    private int flagFilter = 0;
    private ViewPager.OnPageChangeListener pageListener;
    int position = 0;
    boolean end = false;
    Timer timer;
    Chip terbaluTerlamaChip;

    ChipGroup informasiSortChipGroup;

//    MaterialCardView infoFilterCard;
//    TextView infoFilterCardText;

    LinearLayout loadingContainer, failedContainer;
    Button refreshInformasi;


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

        terbaluTerlamaChip = findViewById(R.id.informasi_terbaru_chip);
        informasiSortChipGroup = (ChipGroup) findViewById(R.id.informasi_sort_chip_group);
        informasiSortChipGroup.setSingleSelection(true);
        informasiSortChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, @IdRes int checkedId) {
                informasiArrayList.clear();
                if (checkedId == R.id.informasi_terbaru_chip) {
                    getData(0);
                }
                else if (checkedId == R.id.informasi_terlama_chip) {
                    getData(1);
                }

                else if (checkedId == R.id.informasi_terpopuler_chip) {
                    getData(2);
                }
                else {
                    getData(0);
                }
            }
        });


        loadingContainer = findViewById(R.id.informasi_loading_container);
        failedContainer = findViewById(R.id.informasi_failed_container);
        informasiScrollView = findViewById(R.id.informasi_scroll_view);
//        filterFab = findViewById(R.id.informasi_filter_fab);
//        infoFilterCard = findViewById(R.id.sorting_info_card);
//        infoFilterCardText = findViewById(R.id.sorting_info_card_text);

//        informasiScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                // the delay of the extension of the FAB is set for 12 items
//                if (scrollY > oldScrollY + 12 && filterFab.isExtended()) {
//                    filterFab.shrink();
//                }
//
//                // the delay of the extension of the FAB is set for 12 items
//                if (scrollY < oldScrollY - 12 && !filterFab.isExtended()) {
//                    filterFab.extend();
//                }
//
//                // if the nestedScrollView is at the first item of the list then the
//                // extended floating action should be in extended state
//                if (scrollY == 0) {
//                    filterFab.extend();
//                }
//            }
//        });


        informasiArrayList = new ArrayList<>();
        informasiKaroselArrayList = new ArrayList<>();
        getData(flagFilter);

        recyclerView = findViewById(R.id.blog_list_view);
//        recyclerViewKarosel = findViewById(R.id.blog_karosel_view);
        informasiListAdapter = new InformasiListAdapter(this, informasiArrayList);
//        informasiKaroselListAdapter = new InformasiKaroselListAdapter(this, informasiKaroselArrayList);
//        gridLayoutManager = new GridLayoutManager(this, 2);
        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManagerKarosel = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerViewKarosel.setLayoutManager(linearLayoutManagerKarosel);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                linearLayoutManagerKarosel.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerViewKarosel);
//
//        filterFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FilterInformasiFragment filterInformasiFragment =
//                        FilterInformasiFragment.newInstance();
//                filterInformasiFragment.show(getSupportFragmentManager(),
//                        "add_photo_dialog_fragment");
//            }
//        });

//        // date picker builder
//        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
//        materialDateBuilder.setTitleText("Pilih tanggal mulai");
//
//        final MaterialDatePicker startDatePicker = materialDateBuilder.build();
//        startDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
//            @Override
//            public void onPositiveButtonClick(Long selectedDate) {
//                // link: https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
//                // user has selected a date
//                // format the date and set the text of the input box to be the selected date
//                // right now this format is hard-coded, this will change
//                ;
//                // Get the offset from our timezone and UTC.
//                TimeZone timeZoneUTC = TimeZone.getDefault();
//                // It will be negative, so that's the -1
//                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
//
//                // Create a date format, then a date object with our offset
//                SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
//                Date date = new Date(selectedDate + offsetFromUTC);
//                filterStart = simpleFormat.format(date);
//                filterStartFrom.setText(simpleFormat.format(date));
//            }
//        });
//
//
//        materialDateBuilder.setTitleText("Pilih tanggal akhir");
//        final MaterialDatePicker endDatePicker = materialDateBuilder.build();
//
//        endDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
//            @Override
//            public void onPositiveButtonClick(Long selectedDate) {
//                // link: https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
//                // user has selected a date
//                // format the date and set the text of the input box to be the selected date
//                // right now this format is hard-coded, this will change
//                ;
//                // Get the offset from our timezone and UTC.
//                TimeZone timeZoneUTC = TimeZone.getDefault();
//                // It will be negative, so that's the -1
//                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
//
//                // Create a date format, then a date object with our offset
//                SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
//                Date date = new Date(selectedDate + offsetFromUTC);
//                filterEnd = simpleFormat.format(date);
//                filterEndFrom.setText(simpleFormat.format(date));
//            }
//        });
//
//        barangDialogView = this.getLayoutInflater().inflate(R.layout.dialog_filter_date, null);
//        filterStartFrom= barangDialogView.findViewById(R.id.start_date_button);
//        filterEndFrom = barangDialogView.findViewById(R.id.end_date_button);
//        filterStartFrom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startDatePicker.show(getSupportFragmentManager(), "DATE_START_PICKER");
//            }
//        });
//
//        filterEndFrom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                endDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
//            }
//        });
//
//        barangDialog = new MaterialAlertDialogBuilder(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar)
//                .setTitle("Tentukan filter informasi")
//                .setView(barangDialogView)
//                .setPositiveButton("Terapkan filter", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//
//                    }
//                })
//                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });

//        Button informasiFilterButton = findViewById(R.id.informasi_filter_button);
//        informasiFilterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                barangDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialogInterface) {
//                        informasiFilterButton.setText(filterStart + " - " + filterEnd);
//                    }
//                });
//                barangDialog.show();
//            }
//        });

//        ImageButton likeButtonEx = findViewById(R.id.informasi_like_button);
//        TextView likeCountEx = findViewById(R.id.informasi_like_count);
//        likeButtonEx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                likeButtonEx.setBackgroundTintList(getResources().getColorStateList(R.color.secondaryDarkColor));
//            }
//        });
    }


    public void getData(int flag) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<InformasiResponse> informasiResponseCall = getData.getInformasi(flag);
            informasiResponseCall.enqueue(new Callback<InformasiResponse>() {
            @Override
            public void onResponse(Call<InformasiResponse> call, Response<InformasiResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    recyclerView.setAdapter(informasiListAdapter);
//                    recyclerViewKarosel.setAdapter(informasiKaroselListAdapter);
//                    informasiKaroselArrayList.clear();
                    informasiArrayList.clear();

//                    ArrayList<Informasi> tempList = new ArrayList<>(response.body().getInformasi());
//                    Collections.sort(tempList, new Comparator<Informasi>() {
//                        @Override
//                        public int compare(Informasi rhs, Informasi lhs) {
//                            return lhs.getDilihat().compareTo(rhs.getDilihat());
//                        }
//                    });

//                    int duar = tempList.size();
//
//                    for (int i=0; i<duar-1; i++) {
//                        if (i == 3) {
//                            break;
//                        }
//                        else {
//                            informasiKaroselArrayList.add(tempList.get(i));
//                        }
//                    }

//                    timer = new Timer();
//                    timer.scheduleAtFixedRate(new AutoScrollTask(), 2000, 5000);
//                    position = 0;
//                    end = false;

                    informasiArrayList.addAll(response.body().getInformasi());
                    informasiListAdapter.notifyDataSetChanged();
//                    informasiKaroselListAdapter.notifyDataSetChanged();
                    setInformasiContainerVisible();
                    if (flag == 0) {
                        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Berhasil menampilkan informasi dari terbaru ke lama", Snackbar.LENGTH_SHORT).show();
                    }
                    else if (flag == 1) {
                        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Berhasil menampilkan informasi dari lama ke terbaru", Snackbar.LENGTH_SHORT).show();
                    }
                    else if (flag == 2) {
                        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Berhasil menampilkan informasi dari yang terpopuler", Snackbar.LENGTH_SHORT).show();
                    }
                }
                else {
                    setFailedContainerVisible();
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InformasiResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void setFailedContainerVisible() {
//        filterFab.setVisibility(GONE);
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        informasiScrollView.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
//        filterFab.setVisibility(GONE);
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        informasiScrollView.setVisibility(GONE);
    }

    public void setInformasiContainerVisible() {
//        filterFab.setVisibility(View.VISIBLE);
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        informasiScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(String item) {
        if (item.equals("Terpopuler")) {
            getData(2);
//            infoFilterCard.setVisibility(View.VISIBLE);
//            infoFilterCardText.setText("Informasi di tampilkan berdasaran popularitas");
        }
        else if (item.equals("Terbaru ke lama")) {
            getData(0);
//            infoFilterCard.setVisibility(View.VISIBLE);
//            infoFilterCardText.setText("Informasi di tampilkan berdasaran terbaru ke lama");
        }
        else if (item.equals("Lama ke terbaru")) {
            getData(1);
//            infoFilterCard.setVisibility(View.VISIBLE);
//            infoFilterCardText.setText("Informasi di tampilkan berdasaran lama ke terbaru");
        }
    }

//    private class AutoScrollTask extends TimerTask {
//        @Override
//        public void run() {
//            if(position == informasiKaroselArrayList.size() -1){
//                end = true;
//            } else if (position == 0) {
//                end = false;
//            }
//            if(!end){
//                position++;
//            } else {
//                position--;
//            }
//            recyclerViewKarosel.smoothScrollToPosition(position);
//        }
//    }
}