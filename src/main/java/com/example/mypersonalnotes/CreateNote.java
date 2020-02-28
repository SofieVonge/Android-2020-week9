package com.example.mypersonalnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class CreateNote extends AppCompatActivity {

    private EditText headline;
    private EditText body;
    private boolean isEditing = false;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        headline = findViewById(R.id.headline);
        body = findViewById(R.id.body);
        Intent intent = getIntent();
        position = intent.getIntExtra(MainActivity.key, -1);

        if(position >= 0)
        {
            Note note = NoteStorage.notes.get(position);
            headline.setText(note.headline);
            body.setText(note.body);
            isEditing = true;
        }

    }

    public void save(View view)
    {
        String headlineText = headline.getText().toString();
        String bodyText = body.getText().toString();
        Note note = new Note(headlineText, bodyText);

        if(isEditing)
        {
            NoteStorage.notes.set(position, note);
            isEditing = false;

        } else {

            NoteStorage.notes.add(note);
        }


    }
}
