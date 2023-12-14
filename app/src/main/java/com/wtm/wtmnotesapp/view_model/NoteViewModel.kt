package com.wtm.wtmnotesapp.view_model

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.wtm.wtmnotesapp.models.Note
import com.wtm.wtmnotesapp.room.AppDatabase
import com.wtm.wtmnotesapp.room.DatabaseConfig
import kotlinx.coroutines.launch

class NoteViewModel(val applicationn: Application) : AndroidViewModel(applicationn) {
     fun saveNote(title: String, content: String){
         //Creating a Note instance
          val note = Note(
               title = title,
               content = content
          )
         //Calling the save function of the database
          var db = DatabaseConfig.getInstance(applicationn)

          viewModelScope.launch {
               db.noteDao().saveNote(note)
          }

     }
}