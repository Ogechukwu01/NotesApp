package com.wtm.wtmnotesapp.room

import androidx.room.Dao
import androidx.room.Insert
import com.wtm.wtmnotesapp.models.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun saveNote(note: Note)
}