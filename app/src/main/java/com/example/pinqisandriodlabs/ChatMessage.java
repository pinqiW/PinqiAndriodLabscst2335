package com.example.pinqisandriodlabs;

public class ChatMessage {

    String message;
    boolean sendOrReceive;
    String timeSent;
    public ChatMessage(String m, String t, boolean type){
        message = m;
        timeSent = t;
        sendOrReceive = type;
    }

}