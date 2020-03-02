package com.example.mypersonalnotes.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypersonalnotes.NoteStorage;
import com.example.mypersonalnotes.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public NoteClickListener listener;
    public TextView textView;
    public int row = -1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final static String notes = "notes";

    public ViewHolder(@NonNull View itemView, NoteClickListener listener) {
        super(itemView);
        this.listener = listener;
        //laver itemView til et linearLayout
        LinearLayout linearLayout = (LinearLayout) itemView;
        textView = linearLayout.findViewById(R.id.textView);
        linearLayout.setOnClickListener(this);

       /* this will break my onClickListener for the recyclerview

       textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int row = getAdapterPosition();
                String id = NoteStorage.notes.get(row).getId();
                deleteNote(id);

                return true;
            }
        });*/
    }

    private void deleteNote(String id) {
        //delete a document in a collection based on the id
        DocumentReference docRef = db.collection(notes).document(id);
        docRef.delete();
    }

    @Override
    public void onClick(View v) {
        listener.onClick(getAdapterPosition());

    }


    public void setPosition(int position) {
        this.row = position;
        textView.setText(NoteStorage.notes.get(position).getHeadline());
    }

    public interface NoteClickListener{
        void onClick(int position);
    }


}