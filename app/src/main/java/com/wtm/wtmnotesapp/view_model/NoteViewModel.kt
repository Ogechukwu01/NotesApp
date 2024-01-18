package com.wtm.wtmnotesapp.view_model

import android.app.Application
import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.wtm.wtmnotesapp.models.Note
import com.wtm.wtmnotesapp.room.AppDatabase
import com.wtm.wtmnotesapp.room.DatabaseConfig
import kotlinx.coroutines.launch

class NoteViewModel(val applicationn: Application) : AndroidViewModel(applicationn) {
     //Calling the save function of the database
     private var db = DatabaseConfig.getInstance(applicationn)

     private fun getStartOfDay(currentTimeMillis: Long): Long {
          val calendar = Calendar.getInstance().apply {
               timeInMillis = currentTimeMillis
               set(Calendar.HOUR_OF_DAY, 0)
               set(Calendar.MINUTE, 0)
               set(Calendar.SECOND, 0)
               set(Calendar.MILLISECOND, 0)
          }
          return calendar.timeInMillis
     }
     fun saveNote(title: String, content: String){
          val currentTimeMillis = System.currentTimeMillis()
          if(title.isNullOrEmpty() || content.isNullOrEmpty())return
         //Creating a Note instance
          val note = Note(
               title = title,
               content = content,
               dateTime = System.currentTimeMillis(),
          )

          viewModelScope.launch {
               db.noteDao().saveNote(note)
          }

     }

     fun getAllNotes(): LiveData<List<Note>> {
          return db.noteDao().fetchNotes()
     }

     fun getNote(noteId: String): LiveData<Note>{
          return db.noteDao().fetchNote(noteId)
     }

     fun deleteNote(note: Note){
          viewModelScope.launch {
               db.noteDao().deleteNote(note)
          }
     }

     fun updateNote(note: Note){
          viewModelScope.launch {
               db.noteDao().updateNote(note)
          }
     }
}