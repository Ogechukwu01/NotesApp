package com.wtm.wtmnotesapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.wtm.wtmnotesapp.Routes
import com.wtm.wtmnotesapp.models.Note
import com.wtm.wtmnotesapp.view_model.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(navController: NavController, noteId: String) {
    val noteViewModel: NoteViewModel = viewModel()
    val note by noteViewModel.getNote(noteId).observeAsState()
    var title by rememberSaveable{ mutableStateOf( "") }
    var content by rememberSaveable{ mutableStateOf("") }
    var isEditMode by rememberSaveable{ mutableStateOf(false) }

    fun activateEditMode(){
        isEditMode = true
        title = note?.title ?: ""
        content = note?.content ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text="Note Details") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    if(isEditMode){
                        //show save icon
                        IconButton(onClick = {
                            val updatedNote: Note = note!!.copy(
                                title=title,
                                content=content
                            )
                            noteViewModel.updateNote(updatedNote)
                            isEditMode = false
                        }){
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Save note"
                            )
                        }
                    }else{
                        //show edit icon
                        IconButton(onClick = {
                            activateEditMode()
                        }){
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit note"
                            )
                        }
                    }
                    IconButton(onClick = {
                        noteViewModel.deleteNote(note!!)

                        navController.popBackStack()
                    }){
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete note"
                        )
                    }
                }
            )
        }
    ){paddingValues->
        Column(modifier = Modifier.padding(paddingValues)) {
            if(isEditMode){
                TextField(
                    value = title ?: "",
                    onValueChange = {value-> title=value},
                    modifier = Modifier.fillMaxWidth(),
                    label = {Text("Title")}
                )
                TextField(
                    value = content ?: "",
                    onValueChange = {value-> content=value},
                    modifier = Modifier.fillMaxWidth(),
                    label = {Text("Content")}
                )
            }else{
                Text(
                    note?.title ?: "No Title",
                    modifier = Modifier.fillMaxWidth().clickable {
                        activateEditMode()
                    }
                )
                Text(
                    note?.content ?: "No Content",
                    modifier = Modifier.fillMaxWidth().clickable {
                        activateEditMode()
                    }
                )
            }

        }
    }
}