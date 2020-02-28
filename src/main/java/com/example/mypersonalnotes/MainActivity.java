package com.example.mypersonalnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;


import com.example.mypersonalnotes.adapter.MyRecycleViewAdapter;
import com.example.mypersonalnotes.view.ViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewHolder.NoteClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyRecycleViewAdapter adapter;
    private Intent intent;
    public static final String key = "NOTE";



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
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setHeadlines(getListOfHeadlines());
        adapter.notifyDataSetChanged();
    }



    private ArrayList<String> getListOfHeadlines()
    {
        ArrayList<String> headlines = new ArrayList<>();

        for(Note note : NoteStorage.notes)
        {
            String headline = note.headline;
            headlines.add(headline);
        }

        return headlines;
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
