package com.example.mypersonalnotes.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mypersonalnotes.NoteStorage;
import com.example.mypersonalnotes.R;
import com.example.mypersonalnotes.view.ViewHolder;

import java.util.ArrayList;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {


    private ViewHolder.NoteClickListener listener;


    public MyRecycleViewAdapter(ViewHolder.NoteClickListener listener){
        this.listener = listener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout we made
        LinearLayout ll = (LinearLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.customrow, parent, false);

        return new ViewHolder(ll, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setPosition(position);
    }

    @Override
    //elementer i listen
    public int getItemCount() {
        return NoteStorage.notes.size();
    }
}