package com.wtm.wtmnotesapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavController) {
    var title by remember{mutableStateOf("")}
    var noteContent by remember{mutableStateOf("")}
    Scaffold(
        topBar = {
            TopAppBar(
                title= {Text(text="Add Note")},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        },
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(all=8.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { value-> title =value},
                    label = {Text("Note Title")}
                )
                
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = noteContent,
                    onValueChange = { value -> noteContent = value},
                    label = {Text("Note Content")},
                )
            }
        }
    )
}

@Preview
@Composable
fun AddNoteScreenPreview(){
//    AddNoteScreen(navController)
}