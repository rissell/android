package com.mobiles.firstpartialdelivery;

import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by Kimberly on 13/02/2016.
 */
@SuppressWarnings("serial")
public class Note implements Serializable{

    String header;
    String content;
    String key;
    String folder;
    boolean privacy, important;




    //Falta f - de folder

    public Note(){

    }

    public Note(String h, String c, String k, String f, boolean p, boolean i){

        this.header = h;
        this.content = c;
        this.privacy = p;
        this.important = i;
        this.key = k;
        this.folder = f;


    }
    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
