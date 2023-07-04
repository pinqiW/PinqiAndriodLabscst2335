package com.example.pinqisandriodlabs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ChatMessageDAO {

    @Insert
    public long anyFunctionNameForInsertion(ChatMessage messageToInsert);

    @Update
    public int anyUpdate(ChatMessage updatedMeesage);

                            //@Entity
    @Query("Select * from ChatMessage") // SQL statement
    public List<ChatMessage> getAllMessages();

    @Delete
    public int deleteThisChatMessage(ChatMessage cm);


}
