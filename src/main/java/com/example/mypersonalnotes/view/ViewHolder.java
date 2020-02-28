package com.example.mypersonalnotes.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypersonalnotes.NoteStorage;
import com.example.mypersonalnotes.R;


public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public NoteClickListener listener;
    public TextView textView;
    public int row = -1;

    public ViewHolder(@NonNull View itemView, NoteClickListener listener) {
        super(itemView);
        this.listener = listener;
        //laver itemView til et linearLayout
        LinearLayout linearLayout = (LinearLayout) itemView;
        textView = linearLayout.findViewById(R.id.textView);
        linearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(getAdapterPosition());

    }

    public void setData(int row)
    {
        this.row = row;
        textView.setText(NoteStorage.notes.get(row).headline);
    }

    public interface NoteClickListener{
        void onClick(int position);
    }




}