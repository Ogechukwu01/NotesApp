package com.wtm.wtmnotesapp.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NoteViewModel: ViewModel() {
    var title:String = ""
    var noteContent:String = ""
}