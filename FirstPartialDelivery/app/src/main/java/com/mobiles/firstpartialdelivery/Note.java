package com.mobiles.firstpartialdelivery;

import android.widget.EditText;

/**
 * Created by Kimberly on 13/02/2016.
 */
public class Note {

    String header, content;
    boolean privacy, important;
    //Falta f - de folder

    public Note(){

    }

    public Note(String h, String c, boolean p, boolean i){

        this.header = h;
        this.content = c;
        this.privacy = p;
        this.important = i;


    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
