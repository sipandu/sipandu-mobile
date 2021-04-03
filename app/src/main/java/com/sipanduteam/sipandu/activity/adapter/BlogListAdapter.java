package com.sipanduteam.sipandu.activity.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.Blog;

import java.util.ArrayList;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Blog> blogArrayList;
    private int position;

    public BlogListAdapter(Context context, ArrayList<Blog> blogArrayList) {
        mContext = context;
        this.blogArrayList = blogArrayList;
    }

    @NonNull
    @Override
    public BlogListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.blog_card_layout, parent, false);
        return new BlogListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogListAdapter.ViewHolder holder, int position) {
        holder.blogTitle.setText(blogArrayList.get(position).getTitle());
        holder.blogKeterangan.setText(blogArrayList.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return blogArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView blogTitle;
        private AppCompatTextView blogKeterangan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blogTitle = itemView.findViewById(R.id.blog_title);
            blogKeterangan = itemView.findViewById(R.id.blog_keterangan);
        }
    }
}
