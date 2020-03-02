package com.example.mypersonalnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CreateNote extends AppCompatActivity {

    private EditText headline;
    private EditText body;
    private boolean isEditing = false;
    private int position;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final static String notes = "notes";
    private String id;


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
            headline.setText(note.getHeadline());
            body.setText(note.getBody());
            id = note.getId();
            isEditing = true;
        }

    }

    protected void onPause()
    {
        super.onPause();
        String headlineText = headline.getText().toString();
        String bodyText = body.getText().toString();

        if(isEditing)
        {

            editNote(headlineText, bodyText, id);
            isEditing = false;

        } else {

            addNewNote(headlineText, bodyText);
        }
    }

    private void editNote(String headline, String body, String id) {
        DocumentReference docRef = db.collection(notes).document(id);

        Map<String, String> map = new HashMap<>();
        //  attribute name  content
        map.put("head", headline);
        map.put("body", body);

        //update the database
        docRef.set(map);
    }

    private void addNewNote(String headline, String body) {
        //reference to the document in the db
        DocumentReference docRef = db.collection(notes).document();

        //a map of a new document in the collection notes
        Map<String, String> map = new HashMap<>();
        //  attribute name  content
        map.put("head", headline);
        map.put("body", body);

        //add the note to the database
        docRef.set(map);
    }

}
