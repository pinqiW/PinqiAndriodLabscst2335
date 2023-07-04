package com.example.pinqisandriodlabs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author pinqiW
 * @version 1.0
 */

@Entity
public class ChatMessage {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id")
    public long id;

    @ColumnInfo(name="Message")
    String message;

    @ColumnInfo(name = "SendOrReceive")
    boolean sendOrReceive;

    @ColumnInfo(name = "TimeSent")
    String timeSent;
    public ChatMessage( String m, String t, boolean type){
        message = m;
        timeSent = t;
        sendOrReceive = type;
    }


    public ChatMessage(){

    }
}