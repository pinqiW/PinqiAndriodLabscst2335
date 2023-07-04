package com.example.pinqisandriodlabs;

import androidx.room.Database;

@Database(entities = {ChatMessage.class},version = 1)
public abstract class MessageDatabase {

    public abstract ChatMessageDAO cmDAO(); // only 1 function for how to interact
}
