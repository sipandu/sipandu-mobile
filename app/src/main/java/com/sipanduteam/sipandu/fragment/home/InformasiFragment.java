package com.sipanduteam.sipandu.fragment.home;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.HomeActivity;
import com.sipanduteam.sipandu.activity.informasi.InformasiActivity;
import com.sipanduteam.sipandu.adapter.InformasiKaroselListAdapter;
import com.sipanduteam.sipandu.adapter.InformasiListAdapter;
import com.sipanduteam.sipandu.fragment.widget.FilterInformasiFragment;
import com.sipanduteam.sipandu.model.Informasi;
import com.sipanduteam.sipandu.viewmodel.InformasiBerandaViewModel;
import com.sipanduteam.sipandu.viewmodel.InformasiViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.GONE;

public class InformasiFragment extends Fragment {


    private ArrayList<Informasi> informasiArrayList, informasiKaroselArrayList;
    private InformasiListAdapter informasiListAdapter;
    InformasiKaroselListAdapter informasiKaroselListAdapter;
    private RecyclerView recyclerView, recyclerViewKarosel;
    private LinearLayoutManager linearLayoutManager, linearLayoutManagerKarosel;
    private ScrollView informasiScrollView;
    private int flagFilter = 0;
    int position = 0;
    boolean end = false;
    Timer timer;
    LinearLayout loadingContainer, failedContainer;
    Button refreshInformasi;
    InformasiBerandaViewModel informasiBerandaViewModel;
    ChipGroup kegiatanFilterChipGroup;
    Integer flagAnjay = null;

    public InformasiFragment() {
        // Required empty public constructor
    }

    public static InformasiFragment newInstance() {
        return new InformasiFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (flagAnjay == null) {
            Log.d("sini misalnya", "fragment created");
            flagAnjay = 1;
        }
        informasiArrayList = new ArrayList<>();
        informasiKaroselArrayList = new ArrayList<>();
        informasiBerandaViewModel = ViewModelProviders.of(getActivity()).get(InformasiBerandaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (flagAnjay == null) {
            Log.d("sini 2", "null");
        }
        else {
            Log.d("sini 2", String.valueOf(flagAnjay));
        }

        loadingContainer = view.findViewById(R.id.informasi_loading_container);
        failedContainer = view.findViewById(R.id.informasi_failed_container);
        informasiScrollView = view.findViewById(R.id.informasi_scroll_view);
        recyclerView = view.findViewById(R.id.informasi_list_view);
        recyclerViewKarosel = view.findViewById(R.id.informasi_karosel_view);
        informasiListAdapter = new InformasiListAdapter(getContext(), informasiArrayList);
        informasiKaroselListAdapter = new InformasiKaroselListAdapter(getContext(), informasiKaroselArrayList);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManagerKarosel = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewKarosel.setLayoutManager(linearLayoutManagerKarosel);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewKarosel);

        kegiatanFilterChipGroup = (ChipGroup) view.findViewById(R.id.informasi_sort_chip_group);
        kegiatanFilterChipGroup.setSingleSelection(true);
//        kegiatanFilterChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(ChipGroup group, @IdRes int checkedId) {
//                Log.d("duar masuk duar", "apaje");
//                kegiatanArrayList.clear();
//                if (checkedId == R.id.kegiatan_unfinished_chip) {
//                    for (int i=0; i<kegiatanArrayListFilter.size(); i++) {
//                        if (kegiatanArrayListFilter.get(i).getStatus() == 0) {
//                            kegiatanArrayList.add(kegiatanArrayListFilter.get(i));
//                        }
//                    }
//                }
//                else if (checkedId == R.id.kegiatan_ongoing_chip) {
//                    for (int i=0; i<kegiatanArrayListFilter.size(); i++) {
//                        if (kegiatanArrayListFilter.get(i).getStatus() == 1) {
//                            kegiatanArrayList.add(kegiatanArrayListFilter.get(i));
//                        }
//                    }
//                }
//
//                else if (checkedId == R.id.kegiatan_finished_chip) {
//                    for (int i=0; i<kegiatanArrayListFilter.size(); i++) {
//                        if (kegiatanArrayListFilter.get(i).getStatus() == 2) {
//                            kegiatanArrayList.add(kegiatanArrayListFilter.get(i));
//                        }
//                    }
//                }
//                else {
//                    kegiatanArrayList.addAll(kegiatanArrayListFilter);
//                    kegiatanEmptyText.setText("Tidak ada kegiatan");
//                }
//                kegiatanListAdapter.notifyDataSetChanged();
//            }
//        });


        getData(flagFilter);
    }

    private void getData(int flag) {
        informasiBerandaViewModel.init();
        setLoadingContainerVisible();
        informasiBerandaViewModel.getInformasiRepository().observe(getViewLifecycleOwner(), informasiResponse -> {
            List<Informasi> informasiList = informasiResponse.getInformasi();
            recyclerView.setAdapter(informasiListAdapter);
            recyclerViewKarosel.setAdapter(informasiKaroselListAdapter);
            informasiKaroselArrayList.clear();
            informasiArrayList.clear();

            ArrayList<Informasi> tempList = new ArrayList<>(informasiList);
            Collections.sort(tempList, new Comparator<Informasi>() {
                @Override
                public int compare(Informasi rhs, Informasi lhs) {
                    return lhs.getDilihat().compareTo(rhs.getDilihat());
                }
            });
            int duar = tempList.size();
            for (int i=0; i<duar-1; i++) {
                if (i == 3) {
                    break;
                }
                else {
                    informasiKaroselArrayList.add(tempList.get(i));
                }
            }

            timer = new Timer();
            timer.scheduleAtFixedRate(new AutoScrollTask(), 2000, 5000);
            position = 0;
            end = false;
            informasiArrayList.addAll(informasiList);
            informasiListAdapter.notifyDataSetChanged();
            informasiKaroselListAdapter.notifyDataSetChanged();
            setInformasiContainerVisible();
        });
    }
    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        informasiScrollView.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        informasiScrollView.setVisibility(GONE);
    }

    public void setInformasiContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        informasiScrollView.setVisibility(View.VISIBLE);
    }

    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            if(position == informasiKaroselArrayList.size() -1){
                end = true;
            } else if (position == 0) {
                end = false;
            }
            if(!end){
                position++;
            } else {
                position--;
            }
            recyclerViewKarosel.smoothScrollToPosition(position);
        }
    }
}