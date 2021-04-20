package com.sipanduteam.sipandu.fragment.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sipanduteam.sipandu.R;

public class FilterInformasiFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    View v;
    Button popular, newOld, oldNew;

    private ItemClickListener mListener;

    public static FilterInformasiFragment newInstance() {
        return new FilterInformasiFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_filter_informasi, container, false);
        return v;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.filter_populer).setOnClickListener(this);
        view.findViewById(R.id.filter_new_old).setOnClickListener(this);
        view.findViewById(R.id.filter_old_new).setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override public void onClick(View view) {
        Button button = (Button) view;
        mListener.onItemClick(button.getText().toString());
        dismiss();
    }
    public interface ItemClickListener {
        void onItemClick(String item);
    }
}
