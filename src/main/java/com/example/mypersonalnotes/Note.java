package com.example.mypersonalnotes;

public class Note {

    public String headline;
    public String body;
    public String id;


    public Note(String headline, String body, String id) {
        this.headline = headline;
        this.body = body;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
