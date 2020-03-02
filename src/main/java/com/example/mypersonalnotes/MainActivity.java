package com.example.mypersonalnotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;


import com.example.mypersonalnotes.adapter.MyRecycleViewAdapter;
import com.example.mypersonalnotes.view.ViewHolder;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewHolder.NoteClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyRecycleViewAdapter adapter;
    private Intent intent;
    public static final String key = "NOTE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final static String notes = "notes";
    private ArrayList<String> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecycleViewAdapter(this);
        recyclerView.setAdapter(adapter);

        intent = new Intent(this, CreateNote.class);

        startNoteListener();


    }

    @Override
    protected void onResume() {
        super.onResume();
        startNoteListener();


    }

    private void startNoteListener() {
        //listen to changes in db
        db.collection(notes).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot values, @Nullable FirebaseFirestoreException e) {
                NoteStorage.notes.clear();
                for (DocumentSnapshot snap: values.getDocuments())
                {
                    NoteStorage.notes.add(new Note (snap.get("head").toString(), snap.get("body").toString(), snap.getId()));
                }
                adapter.notifyDataSetChanged();
            }
        });

    }



    public void plus(View view)
    {
        startActivity(intent);

    }

    @Override
    public void onClick(int position) {
        intent.putExtra(key, position);
        startActivity(intent);
    }
}
